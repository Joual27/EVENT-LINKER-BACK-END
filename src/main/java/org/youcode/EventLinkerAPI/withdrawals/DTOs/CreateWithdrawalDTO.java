package org.youcode.EventLinkerAPI.withdrawals.DTOs;

import jakarta.validation.constraints.NotNull;

public record CreateWithdrawalDTO(@NotNull Long amount ) {
}
