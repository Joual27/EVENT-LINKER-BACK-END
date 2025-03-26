package org.youcode.EventLinkerAPI.review.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record EmbeddedReviewDTO(int rating, String comment, EmbeddedUserDTO reviewer , EmbeddedUserDTO reviewee , LocalDateTime createdAt ) {
}
