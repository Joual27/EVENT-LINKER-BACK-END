package org.youcode.EventLinkerAPI.AnnouncementSkill.DTOs;

import org.youcode.EventLinkerAPI.announcement.DTOs.EmbeddedAnnouncementDTO;
import org.youcode.EventLinkerAPI.skill.DTOs.EmbeddedSkillDTO;

public record EmbeddedAnnouncementSkillDTO(EmbeddedAnnouncementDTO announcement , EmbeddedSkillDTO skill , boolean acceptsNonOrganizations) {
}
