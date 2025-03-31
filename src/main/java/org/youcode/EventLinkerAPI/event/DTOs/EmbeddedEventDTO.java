package org.youcode.EventLinkerAPI.event.DTOs;

import java.time.LocalDateTime;

public record EmbeddedEventDTO(String title , String description , String imgUrl , String location, LocalDateTime date) {
}
