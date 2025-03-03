package org.youcode.EventLinkerAPI.withdrawals;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;
import org.youcode.EventLinkerAPI.withdrawals.DTOs.CreateWithdrawalDTO;
import org.youcode.EventLinkerAPI.withdrawals.DTOs.WorkerWithdrawalResponseDTO;
import org.youcode.EventLinkerAPI.withdrawals.interfaces.WithdrawalService;

@AllArgsConstructor
@RequestMapping("/api/v1/worker/withdrawals")
@RestController
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @PostMapping("/{mode}")
    public ResponseEntity<SuccessDTO<WorkerWithdrawalResponseDTO>> withdraw(@RequestBody @Valid CreateWithdrawalDTO req , @PathVariable("mode") String mode){
        WorkerWithdrawalResponseDTO res = withdrawalService.withdraw(req , mode);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Withdrawal Request Created Successfully !" , res) , HttpStatus.CREATED);
    }
}
