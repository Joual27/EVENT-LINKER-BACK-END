package org.youcode.EventLinkerAPI.user.DTOs;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record UpdateProfileDTO(@Nullable String bio ,@Nullable MultipartFile profileImg) {
}
