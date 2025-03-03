package org.youcode.EventLinkerAPI.payment.DTOs;

import jakarta.validation.constraints.NotNull;

public record ConfirmPaymentIntentDTO(@NotNull String paymentIntentId) {
}
