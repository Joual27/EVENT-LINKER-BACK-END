package org.youcode.EventLinkerAPI.skill.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.skill.DTOs.EmbeddedSkillDTO;
import org.youcode.EventLinkerAPI.skill.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper extends BaseEmbeddedMapper<Skill , EmbeddedSkillDTO> {
}
