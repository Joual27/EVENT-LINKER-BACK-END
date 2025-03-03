package org.youcode.EventLinkerAPI.review.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

public record EmbeddedReviewDTO(int rating, String comment, EmbeddedUserDTO reviewer , EmbeddedUserDTO reviewee ) {
}
