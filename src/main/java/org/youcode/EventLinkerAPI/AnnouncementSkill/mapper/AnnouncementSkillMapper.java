package org.youcode.EventLinkerAPI.AnnouncementSkill.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.AnnouncementSkill.AnnouncementSkill;
import org.youcode.EventLinkerAPI.AnnouncementSkill.DTOs.EmbeddedAnnouncementSkillDTO;
import org.youcode.EventLinkerAPI.announcement.mapper.AnnouncementMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.skill.mapper.SkillMapper;

@Mapper(componentModel = "spring" , uses = {AnnouncementMapper.class , SkillMapper.class })
public interface AnnouncementSkillMapper extends BaseEmbeddedMapper<AnnouncementSkill , EmbeddedAnnouncementSkillDTO> {
}
