package org.youcode.EventLinkerAPI.message.DTOs;


import java.time.LocalDateTime;

public record MessageResponseDTO(
        Long id,
        LocalDateTime sentAt,
        boolean delivered,
        LocalDateTime deliveredAt,
        LocalDateTime seenAt,
        String content,
        Long dmId,  // Add dmId
        Long userId, // Add userId
        String username
) {}