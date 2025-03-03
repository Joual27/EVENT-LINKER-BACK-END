package org.youcode.EventLinkerAPI.review.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record   SubmitReviewDTO(@NotNull @Min(0) @Max(5) int rating ,@NotNull String comment , @NotNull Long applicationId , @NotNull Long revieweeId) {
}
