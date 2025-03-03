package org.youcode.EventLinkerAPI.withdrawals.DTOs;

public record WorkerWithdrawalResponseDTO(double amount , String currency , String status , String paymentMode , String type , String onBoardingLink) {
}
