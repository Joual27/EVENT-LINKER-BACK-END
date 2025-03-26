package org.youcode.EventLinkerAPI.application.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateApplicationDTO(@NotNull @Min(0) double price , @NotNull String letter , @NotNull Long announcementId) {
}
