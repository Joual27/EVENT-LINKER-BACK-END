package org.youcode.EventLinkerAPI.review;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.exceptions.InvalidReviewConstraintsException;
import org.youcode.EventLinkerAPI.review.DTOs.AverageReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.ReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.SubmitReviewDTO;
import org.youcode.EventLinkerAPI.review.interfaces.ReviewService;
import org.youcode.EventLinkerAPI.review.mapper.ReviewMapper;
import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ReviewServiceImp implements ReviewService {
    private final ReviewDAO reviewDAO;
    private final ReviewMapper reviewMapper;
    private final ApplicationService applicationService;
    private final UserService userService;

    @Override
    public ReviewResponseDTO submitReview(SubmitReviewDTO data){
        Application existingApplication = applicationService.getApplicationEntityById(data.applicationId());
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User reviewee = userService.getUserEntityById(data.revieweeId());
        validateRolesConstraints(reviewer , reviewee);
        validateApplicationPaymentStatus(existingApplication);
        checkForExistingReview(reviewer , reviewee , existingApplication);
        Review reviewToSubmit = reviewMapper.toEntity(data);
        Review reviewWithData = fillReviewData(reviewToSubmit , existingApplication , reviewer , reviewee);
        Review createdReview = reviewDAO.save(reviewWithData);
        return reviewMapper.toResponseDTO(createdReview);
    }

    @Override
    public AverageReviewResponseDTO getUserAvgReview() {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new AverageReviewResponseDTO(new EmbeddedUserDTO(authenticatedUser.getEmail()) , reviewDAO.getAvgRatingByReviewee(authenticatedUser) , getUserReviewsCount(authenticatedUser));
    }

    @Override
    public Page<ReviewResponseDTO> getUserReviews(int page , int size) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<Review> reviews = reviewDAO.findByReviewee( pageRequest ,authenticatedUser);
        return reviews.map(reviewMapper::toResponseDTO);
    }


    private void validateRolesConstraints(User reviewer , User reviewee){
        if (reviewer.getUserRole().equals(reviewee.getUserRole())){
            throw new InvalidReviewConstraintsException("Reviewer and reviewee can't have the same role !");
        }
    }

    private void validateApplicationPaymentStatus(Application application){
        if (!application.getPayment().getStatus().equals("succeeded")){
            throw new InvalidReviewConstraintsException("The application should be already payed before giving reviews");
        }
    }

    private void checkForExistingReview(User reviewer , User reviewee , Application application){
        if (reviewDAO.findByReviewerAndRevieweeAndApplication(reviewer , reviewee , application).isPresent()){
            throw new InvalidReviewConstraintsException("You have already gave a review related to this job !");
        }
    }

    private Review fillReviewData(Review review , Application application , User reviewer , User reviewee){
        review.setApplication(application);
        review.setCreatedAt(LocalDateTime.now());
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        return review;
    }

    private int getUserReviewsCount(User user){
        return reviewDAO.countByReviewee(user);
    }

}
