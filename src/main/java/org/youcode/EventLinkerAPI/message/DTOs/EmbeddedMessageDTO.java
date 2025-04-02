package org.youcode.EventLinkerAPI.message.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record EmbeddedMessageDTO(LocalDateTime sentAt , boolean delivered , LocalDateTime deliveredAt, LocalDateTime seenAt , String content , EmbeddedUserDTO user) {
}
