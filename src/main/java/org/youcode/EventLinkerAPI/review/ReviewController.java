package org.youcode.EventLinkerAPI.review;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.review.DTOs.AverageReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.ReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.SubmitReviewDTO;
import org.youcode.EventLinkerAPI.review.interfaces.ReviewService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<SuccessDTO<ReviewResponseDTO>> submitReview(@RequestBody @Valid SubmitReviewDTO req){
        ReviewResponseDTO res = reviewService.submitReview(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "review submitted successfully !" , res ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<ReviewResponseDTO>>> getAllUserReviews(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "5") int size){
        Page<ReviewResponseDTO> res = reviewService.getUserReviews(page, size);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "reviews retrieved successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/avg")
    public ResponseEntity<SuccessDTO<AverageReviewResponseDTO>> getUserAverageReview(){
        AverageReviewResponseDTO res = reviewService.getUserAvgReview();
        return new ResponseEntity<>(new SuccessDTO<>("success" , "AVG USER review retrieved successfully !" , res ), HttpStatus.OK);

    }
}
