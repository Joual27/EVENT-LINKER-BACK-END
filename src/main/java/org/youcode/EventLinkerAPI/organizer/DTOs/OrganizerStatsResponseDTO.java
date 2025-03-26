package org.youcode.EventLinkerAPI.organizer.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.UserStatsResponseDTO;

public record OrganizerStatsResponseDTO(int numberOfCreatedEvents , int numberOfReviews , double avgReview) implements UserStatsResponseDTO {
}
