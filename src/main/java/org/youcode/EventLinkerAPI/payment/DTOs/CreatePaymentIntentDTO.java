package org.youcode.EventLinkerAPI.payment.DTOs;

import jakarta.validation.constraints.NotNull;

public record CreatePaymentIntentDTO(@NotNull String paymentMethodId , @NotNull Long applicationId) {
}
