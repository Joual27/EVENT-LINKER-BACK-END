package org.youcode.EventLinkerAPI.application.DTOs;

import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;
import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record EmbeddedApplicationDTO(EmbeddedUserDTO applicant , LocalDateTime createdAt, ApplicationStatus status) {
}
