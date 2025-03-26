package org.youcode.EventLinkerAPI.announcement.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.youcode.EventLinkerAPI.AnnouncementSkill.DTOs.AnnouncementSkillEmbeddedCreationalDTO;

import java.util.List;

public record UpdateAnnouncementDTO(@NotNull String title, @NotNull String description , @NotNull @NotEmpty List<AnnouncementSkillEmbeddedCreationalDTO> skills) {
}
