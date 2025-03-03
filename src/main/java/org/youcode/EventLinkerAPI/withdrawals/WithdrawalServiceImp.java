package org.youcode.EventLinkerAPI.withdrawals;

import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import com.stripe.param.PayoutCreateParams;
import com.stripe.param.TransferCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.exceptions.InsufficientBalanceException;
import org.youcode.EventLinkerAPI.exceptions.PaymentProcessingException;
import org.youcode.EventLinkerAPI.withdrawals.DTOs.CreateWithdrawalDTO;
import org.youcode.EventLinkerAPI.withdrawals.DTOs.WorkerWithdrawalResponseDTO;
import org.youcode.EventLinkerAPI.withdrawals.enums.WithdrawalStatus;
import org.youcode.EventLinkerAPI.withdrawals.interfaces.WithdrawalService;
import org.youcode.EventLinkerAPI.worker.Worker;
import org.youcode.EventLinkerAPI.worker.interfaces.WorkerService;

import java.time.LocalDateTime;

@Service
public class WithdrawalServiceImp implements WithdrawalService {
    private final String defaultCurrency;
    private final WithdrawalDAO withdrawalDAO;
    private final WorkerService workerService;

    public WithdrawalServiceImp(@Value("${DEFAULT_CURRENCY}") String defaultCurrency,
                                WithdrawalDAO withdrawalDAO,
                                WorkerService workerService) {
        this.defaultCurrency = defaultCurrency;
        this.withdrawalDAO = withdrawalDAO;
        this.workerService = workerService;
    }

    @Transactional
    @Override
    public WorkerWithdrawalResponseDTO withdraw(CreateWithdrawalDTO data, String payoutMode) {
        Worker worker = (Worker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (worker.getBalance() < data.amount()) {
            throw new InsufficientBalanceException("The request amount for withdraw is not available on your balance!");
        }

        try {
            String stripeAccountId = verifyStripeAccountOwning(worker);
            Account connectedAccount = Account.retrieve(stripeAccountId);

            boolean externalAccountFound = connectedAccount.getExternalAccounts()
                    .getData()
                    .stream()
                    .anyMatch(external -> external instanceof BankAccount bankAccount
                            && defaultCurrency.equalsIgnoreCase(bankAccount.getCurrency()));

            boolean isFullyOnboarded = connectedAccount.getPayoutsEnabled() && externalAccountFound;

            if (!isFullyOnboarded) {
                return generateOnboardingResponse(stripeAccountId, payoutMode);
            }

            transferFundsToWorker(stripeAccountId , data.amount() * 100);

            PayoutCreateParams params = PayoutCreateParams.builder()
                    .setAmount(data.amount() * 100)
                    .setCurrency(defaultCurrency)
                    .setMethod(getRequestedPaymentMethod(payoutMode))
                    .build();

            Payout payout;
            try {
                RequestOptions requestOptions = RequestOptions.builder()
                        .setStripeAccount(stripeAccountId)
                        .build();
                payout = Payout.create(params, requestOptions);
            } catch (StripeException e) {
                    throw new PaymentProcessingException(e.getMessage());
            }

            Withdrawal withdrawalToCreate = new Withdrawal();
            withdrawalToCreate.setAmount((double) data.amount());
            withdrawalToCreate.setRequestedOn(LocalDateTime.now());
            withdrawalToCreate.setStatus(WithdrawalStatus.PROCESSING);
            withdrawalToCreate.setWorker(worker);
            withdrawalToCreate.setStripePayoutId(payout.getId());
            withdrawalDAO.save(withdrawalToCreate);
            workerService.postWithdrawalBalanceUpdate(worker, data.amount());

            return new WorkerWithdrawalResponseDTO(
                    data.amount(),
                    payout.getCurrency(),
                    payout.getStatus(),
                    payoutMode,
                    payout.getType(),
                    null
            );

        } catch (StripeException e) {
            throw new PaymentProcessingException("Payout failed: " + e.getMessage());
        }
    }

    private PayoutCreateParams.Method getRequestedPaymentMethod(String payoutMode) {
        return payoutMode.equalsIgnoreCase("instant")
                ? PayoutCreateParams.Method.INSTANT
                : PayoutCreateParams.Method.STANDARD;
    }


    private String verifyStripeAccountOwning(Worker worker) {
        try {
            if (worker.getStripeAccountId() != null) {
                return worker.getStripeAccountId();
            } else {
                AccountCreateParams params = AccountCreateParams.builder()
                        .setType(AccountCreateParams.Type.EXPRESS)
                        .setCountry("US")
                        .setEmail(worker.getEmail())
                        .build();
                Account account = Account.create(params);
                workerService.updateWorkerStripeId(worker, account.getId());
                return account.getId();
            }
        } catch (StripeException e) {
            throw new PaymentProcessingException("Account creation failed: " + e.getMessage());
        }
    }


    private WorkerWithdrawalResponseDTO generateOnboardingResponse(String stripeAccountId, String payoutMode) throws StripeException {
        AccountLinkCreateParams accountLinkParams = AccountLinkCreateParams.builder()
                .setAccount(stripeAccountId)
                .setRefreshUrl("https://placeholder.com/reauth")
                .setReturnUrl("https://placeholder.com/return")
                .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                .build();

        AccountLink accountLink = AccountLink.create(accountLinkParams);

        return new WorkerWithdrawalResponseDTO(
                0,
                null,
                "onboarding_required",
                payoutMode,
                null,
                accountLink.getUrl()
        );
    }

    private void transferFundsToWorker(String workerStripeAccountId, long amountInCents) {
        try {
            TransferCreateParams transferParams = TransferCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency("usd")
                    .setDestination(workerStripeAccountId)
                    .build();

            Transfer transfer = Transfer.create(transferParams);
            System.out.println("Transfer created: " + transfer.getId());
        } catch (StripeException e) {
            throw new PaymentProcessingException("Transfer failed: " + e.getMessage());
        }
    }
}
