package org.youcode.EventLinkerAPI.user.DTOs;

import jakarta.validation.constraints.NotNull;

public record LogoutDTO(@NotNull String token) {
}
