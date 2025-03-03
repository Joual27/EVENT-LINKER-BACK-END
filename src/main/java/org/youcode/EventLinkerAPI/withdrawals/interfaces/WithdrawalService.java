package org.youcode.EventLinkerAPI.withdrawals.interfaces;

import org.youcode.EventLinkerAPI.withdrawals.DTOs.CreateWithdrawalDTO;
import org.youcode.EventLinkerAPI.withdrawals.DTOs.WorkerWithdrawalResponseDTO;


public interface WithdrawalService {
    WorkerWithdrawalResponseDTO withdraw(CreateWithdrawalDTO data , String payoutMode);

}
