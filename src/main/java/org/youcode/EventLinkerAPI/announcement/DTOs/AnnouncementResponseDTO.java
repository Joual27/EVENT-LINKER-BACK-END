package org.youcode.EventLinkerAPI.announcement.DTOs;

import org.youcode.EventLinkerAPI.AnnouncementSkill.DTOs.EmbeddedAnnouncementSkillDTO;
import org.youcode.EventLinkerAPI.announcement.enums.AnnouncementStatus;
import org.youcode.EventLinkerAPI.event.DTOs.EmbeddedEventDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record AnnouncementResponseDTO(Long id , String title , String description , LocalDateTime createdAt , AnnouncementStatus status , EmbeddedEventDTO event , Set<EmbeddedAnnouncementSkillDTO> announcementSkills) {
}
