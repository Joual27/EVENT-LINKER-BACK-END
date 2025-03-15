package org.youcode.EventLinkerAPI.worker.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.UserStatsResponseDTO;

public record WorkerStatsResponseDTO(int completedJobs, int numberOfReviews , double avgReview) implements UserStatsResponseDTO {
}
