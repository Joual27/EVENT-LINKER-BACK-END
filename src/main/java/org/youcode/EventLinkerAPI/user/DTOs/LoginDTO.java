package org.youcode.EventLinkerAPI.user.DTOs;

import jakarta.validation.constraints.NotNull;

public record LoginDTO (@NotNull String email , @NotNull String password){
}
