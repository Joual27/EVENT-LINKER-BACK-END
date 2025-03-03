package org.youcode.EventLinkerAPI.worker.DTOs;

import jakarta.validation.constraints.NotNull;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.shared.utils.validators.interfaces.Unique;
import org.youcode.EventLinkerAPI.user.User;

import java.time.LocalDateTime;

public record WorkerRegistrationDTO(@NotNull @Unique(entity = User.class , field = "username") String username , @NotNull String password , @Unique(entity = User.class , field = "email") @NotNull String email , LocalDateTime createdAt , @NotNull boolean isOrganization) implements BaseRegistrationDTO {
}
