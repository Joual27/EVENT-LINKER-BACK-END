package org.youcode.EventLinkerAPI.message.DTOs;

import jakarta.validation.constraints.NotNull;

public record SendMessageDTO(@NotNull Long dmId ,@NotNull String content) {
}
