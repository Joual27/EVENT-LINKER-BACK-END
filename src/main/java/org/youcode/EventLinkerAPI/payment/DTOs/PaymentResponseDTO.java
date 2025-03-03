package org.youcode.EventLinkerAPI.payment.DTOs;


import java.time.LocalDateTime;

public record PaymentResponseDTO(String clientSecret , String stripePaymentId , double amount , String currency , LocalDateTime processedOn , String status) {
}
