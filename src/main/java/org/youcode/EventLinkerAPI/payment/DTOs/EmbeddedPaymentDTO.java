package org.youcode.EventLinkerAPI.payment.DTOs;

import java.time.LocalDateTime;

public record EmbeddedPaymentDTO(double amount , String status , LocalDateTime processedOn ) {
}
