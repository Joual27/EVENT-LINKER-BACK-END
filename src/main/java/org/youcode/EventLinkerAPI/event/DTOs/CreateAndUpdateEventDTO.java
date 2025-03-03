package org.youcode.EventLinkerAPI.event.DTOs;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateAndUpdateEventDTO(@NotNull String title , @NotNull String description , @NotNull LocalDateTime date , @NotNull String location ) {
}
