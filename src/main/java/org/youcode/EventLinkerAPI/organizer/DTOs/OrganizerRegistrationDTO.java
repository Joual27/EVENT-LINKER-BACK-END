package org.youcode.EventLinkerAPI.organizer.DTOs;

import jakarta.validation.constraints.NotNull;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.shared.utils.validators.interfaces.Unique;
import org.youcode.EventLinkerAPI.user.User;

import java.time.LocalDateTime;

public record OrganizerRegistrationDTO(@NotNull @Unique(entity = User.class , field = "username") String username , @NotNull String password , @NotNull @Unique(entity = User.class , field = "email") String email , LocalDateTime createdAt , @NotNull String organizationName) implements BaseRegistrationDTO {
}
