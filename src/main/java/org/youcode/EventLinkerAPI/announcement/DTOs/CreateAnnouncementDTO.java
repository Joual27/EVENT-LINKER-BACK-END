package org.youcode.EventLinkerAPI.announcement.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.youcode.EventLinkerAPI.AnnouncementSkill.DTOs.AnnouncementSkillEmbeddedCreationalDTO;

import java.util.List;

    public record CreateAnnouncementDTO(@NotNull String title, @NotNull String description , @NotNull @NotEmpty List<AnnouncementSkillEmbeddedCreationalDTO> skills , @NotNull Long eventId) {
}
