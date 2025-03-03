package org.youcode.EventLinkerAPI.review.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.EventLinkerAPI.review.DTOs.AverageReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.ReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.SubmitReviewDTO;
import org.youcode.EventLinkerAPI.review.Review;

public interface ReviewService {
    ReviewResponseDTO submitReview(SubmitReviewDTO data);
    AverageReviewResponseDTO getUserAvgReview();
    Page<ReviewResponseDTO> getUserReviews(int page , int size);
}
