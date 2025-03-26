package org.youcode.EventLinkerAPI.application.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.EventLinkerAPI.announcement.DTOs.EmbeddedAnnouncementDTO;
import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;
import org.youcode.EventLinkerAPI.payment.DTOs.EmbeddedPaymentDTO;
import org.youcode.EventLinkerAPI.review.DTOs.EmbeddedReviewDTO;
import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record ApplicationResponseDTO(Long id  , double price , ApplicationStatus status , LocalDateTime createdAt , String letter , EmbeddedAnnouncementDTO announcement , EmbeddedUserDTO applicant , @JsonInclude(JsonInclude.Include.NON_NULL) EmbeddedPaymentDTO payment) {
}
