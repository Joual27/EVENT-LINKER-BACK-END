package org.youcode.EventLinkerAPI.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.exceptions.ApplicationAlreadyHasPaymentException;
import org.youcode.EventLinkerAPI.exceptions.PaymentProcessingException;
import org.youcode.EventLinkerAPI.payment.DTOs.CreatePaymentIntentDTO;
import org.youcode.EventLinkerAPI.payment.DTOs.PaymentResponseDTO;
import org.youcode.EventLinkerAPI.payment.interfaces.PaymentService;

import java.time.LocalDateTime;


@Service
public class PaymentServiceImp implements PaymentService {
    private final String stripeApiKey;
    private final String defaultCurrency;
    private final ApplicationService applicationService;
    private final PaymentDAO paymentDAO;

    public PaymentServiceImp(@Value("${STRIPE_API_KEY}") String stripeApiKey ,@Value("${DEFAULT_CURRENCY}") String defaultCurrency , ApplicationService applicationService , PaymentDAO paymentDAO){
        this.stripeApiKey = stripeApiKey;
        this.defaultCurrency = defaultCurrency;
        this.applicationService = applicationService;
        this.paymentDAO = paymentDAO;
    }

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public PaymentResponseDTO createPaymentIntent(CreatePaymentIntentDTO data) {
        Application existingApplication = applicationService.getApplicationEntityById(data.applicationId());
        applicationService.verifyPayabilityOfApplication(existingApplication);
        if (applicationAlreadyPayed(existingApplication.getId())){
            throw new ApplicationAlreadyHasPaymentException("This Application Already has a payment !");
        }
        try{
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) existingApplication.getPrice() * 100)
                    .setCurrency(defaultCurrency)
                    .setPaymentMethod(data.paymentMethodId())
                    .setTransferGroup("APPLICATION_"+data.applicationId())
                    .setConfirm(true)
                    .setReturnUrl("https://facebook.com")
                    .build();
            PaymentIntent createdPaymentIntent = PaymentIntent.create(params);
            Payment paymentToCreate = mapDataToPayment(data , existingApplication);
            paymentToCreate.setStatus(createdPaymentIntent.getStatus());
            Payment createdPayment = paymentDAO.save(paymentToCreate);
            if (!"succeeded".equals(createdPaymentIntent.getStatus())){
                throw new PaymentProcessingException("Payment was not captured with Status: " + createdPaymentIntent.getStatus());
            }
            return new PaymentResponseDTO(createdPaymentIntent.getClientSecret() , createdPaymentIntent.getId() , createdPayment.getAmount() , createdPayment.getCurrency() , createdPayment.getProcessedOn() , createdPayment.getStatus());
        }catch (StripeException e){
            throw new PaymentProcessingException(e.getMessage());
        }
    }


    private Payment mapDataToPayment(CreatePaymentIntentDTO data, Application application){
        Payment paymentToCreate = new Payment();
        paymentToCreate.setAmount(application.getPrice());
        paymentToCreate.setCurrency(defaultCurrency);
        paymentToCreate.setProcessedOn(LocalDateTime.now());
        paymentToCreate.setApplication(application);
        return paymentToCreate;
    }

    private boolean applicationAlreadyPayed(Long applicationId){
        if (paymentDAO.findByApplication_Id(applicationId).isPresent()){
            return true;
        }
        return false;
    }



}
