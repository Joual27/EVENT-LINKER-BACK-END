package org.youcode.EventLinkerAPI.review.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

public record AverageReviewResponseDTO(EmbeddedUserDTO user , double averageReview , int reviewsCount) {
}
