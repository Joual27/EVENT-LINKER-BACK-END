package org.youcode.EventLinkerAPI.review.interfaces;
import org.youcode.EventLinkerAPI.review.DTOs.AverageReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.ReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.SubmitReviewDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO submitReview(SubmitReviewDTO data);
    AverageReviewResponseDTO getUserAvgReview();
    PaginationDTO<List<ReviewResponseDTO>> getUserReviews(int page , int size , Long id);
}
