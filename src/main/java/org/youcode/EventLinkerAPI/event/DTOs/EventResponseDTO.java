package org.youcode.EventLinkerAPI.event.DTOs;


import org.youcode.EventLinkerAPI.organizer.DTOs.EmbeddedOrganizerDTO;

import java.time.LocalDateTime;

public record EventResponseDTO(Long id , String description , LocalDateTime date , String location , EmbeddedOrganizerDTO organizer) {
}
