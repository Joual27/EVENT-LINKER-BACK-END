package org.youcode.EventLinkerAPI.review;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.exceptions.InvalidReviewConstraintsException;
import org.youcode.EventLinkerAPI.payment.Payment;
import org.youcode.EventLinkerAPI.review.DTOs.ReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.SubmitReviewDTO;
import org.youcode.EventLinkerAPI.review.mapper.ReviewMapper;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;
import org.youcode.EventLinkerAPI.worker.Worker;
import org.youcode.EventLinkerAPI.organizer.Organizer;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImpTest {

    @Mock private ReviewDAO reviewDAO;
    @Mock private ReviewMapper reviewMapper;
    @Mock private ApplicationService applicationService;
    @Mock private UserService userService;
    @Mock private SecurityContext securityContext;
    @Mock private Authentication authentication;
    @InjectMocks private ReviewServiceImp reviewService;

    private Organizer reviewer;
    private Worker reviewee;
    private Application application;
    private Payment payment;
    private SubmitReviewDTO submitReviewDTO;
    private Review review;
    private Review savedReview;
    private ReviewResponseDTO reviewResponseDTO;

    @BeforeEach
    void setUp() {
        reviewer = new Organizer();
        reviewer.setId(1L);
        reviewer.setUsername("organizer1");
        reviewer.setEmail("organizer@example.com");
        reviewer.setPassword("password");

        reviewee = new Worker();
        reviewee.setId(2L);
        reviewee.setUsername("worker1");
        reviewee.setEmail("worker@example.com");
        reviewee.setPassword("password");

        application = new Application();
        application.setId(1L);

        payment = new Payment();
        payment.setStatus("succeeded");
        application.setPayment(payment);

        submitReviewDTO = new SubmitReviewDTO(4, "TEST!", 1L, 2L);

        review = new Review();
        savedReview = new Review();
        savedReview.setId(1L);
        reviewResponseDTO = new ReviewResponseDTO(1L, "TEST!", 4, LocalDateTime.now(), null, null, null);

        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(reviewer);
    }

    @Test
    void submitReview_Success() {
        when(applicationService.getApplicationEntityById(1L)).thenReturn(application);
        when(userService.getUserEntityById(2L)).thenReturn(reviewee);
        when(reviewDAO.findByReviewerAndRevieweeAndApplication(reviewer, reviewee, application))
                .thenReturn(Optional.empty());
        when(reviewMapper.toEntity(submitReviewDTO)).thenReturn(review);
        when(reviewDAO.save(any(Review.class))).thenReturn(savedReview);
        when(reviewMapper.toResponseDTO(savedReview)).thenReturn(reviewResponseDTO);

        ReviewResponseDTO result = reviewService.submitReview(submitReviewDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(4, result.rating());
        assertEquals("TEST!", result.comment());
    }

    @Test
    void submitReview_Failure_SameRole() {
        Organizer sameRoleReviewee = new Organizer();
        sameRoleReviewee.setId(2L);

        when(applicationService.getApplicationEntityById(1L)).thenReturn(application);
        when(userService.getUserEntityById(2L)).thenReturn(sameRoleReviewee);

        assertThrows(InvalidReviewConstraintsException.class, () -> {
            reviewService.submitReview(submitReviewDTO);
        });
    }

    @Test
    void submitReview_Failure_ReviewAlreadyExists() {
        when(applicationService.getApplicationEntityById(1L)).thenReturn(application);
        when(userService.getUserEntityById(2L)).thenReturn(reviewee);
        when(reviewDAO.findByReviewerAndRevieweeAndApplication(reviewer, reviewee, application))
                .thenReturn(Optional.of(new Review()));

        assertThrows(InvalidReviewConstraintsException.class, () -> {
            reviewService.submitReview(submitReviewDTO);
        });
    }

    @Test
    void submitReview_Failure_PaymentNotSucceeded() {
        Application failedPaymentApp = new Application();
        failedPaymentApp.setId(1L);
        Payment failedPayment = new Payment();
        failedPayment.setStatus("failed");
        failedPaymentApp.setPayment(failedPayment);

        when(applicationService.getApplicationEntityById(1L)).thenReturn(failedPaymentApp);
        when(userService.getUserEntityById(2L)).thenReturn(reviewee);

        assertThrows(InvalidReviewConstraintsException.class, () -> {
            reviewService.submitReview(submitReviewDTO);
        });

        verify(applicationService).getApplicationEntityById(1L);
        verify(userService).getUserEntityById(2L);
        verifyNoInteractions(reviewDAO);
    }
}