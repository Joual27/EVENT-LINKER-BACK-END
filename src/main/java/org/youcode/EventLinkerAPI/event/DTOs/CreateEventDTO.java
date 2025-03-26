package org.youcode.EventLinkerAPI.event.DTOs;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record CreateEventDTO(@NotNull MultipartFile img, @NotNull String title , @NotNull String description , @NotNull LocalDateTime date , @NotNull String location ) {
}
