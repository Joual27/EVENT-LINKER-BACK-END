package org.youcode.EventLinkerAPI.review.DTOs;

import org.youcode.EventLinkerAPI.application.DTOs.EmbeddedApplicationDTO;
import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record ReviewResponseDTO(Long id , String comment , int rating , LocalDateTime createdAt , EmbeddedApplicationDTO application , EmbeddedUserDTO reviewer , EmbeddedUserDTO reviewee) {
}
