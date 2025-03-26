package org.youcode.EventLinkerAPI.event.DTOs;


import org.youcode.EventLinkerAPI.organizer.DTOs.EmbeddedOrganizerDTO;

import java.time.LocalDateTime;

public record EventResponseDTO(Long id ,String title , String description , LocalDateTime date , String imgUrl , String location , EmbeddedOrganizerDTO organizer) {
}
