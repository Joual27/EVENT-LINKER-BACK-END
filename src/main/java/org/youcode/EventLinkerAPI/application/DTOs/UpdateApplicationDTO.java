package org.youcode.EventLinkerAPI.application.DTOs;

import jakarta.validation.constraints.NotNull;

public record UpdateApplicationDTO (@NotNull String letter , @NotNull double price) {
}
