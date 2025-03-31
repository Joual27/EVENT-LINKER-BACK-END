package org.youcode.EventLinkerAPI.skill.interfaces;

import org.youcode.EventLinkerAPI.skill.DTOs.SkillResponseDTO;
import org.youcode.EventLinkerAPI.skill.Skill;

import java.util.List;

public interface SkillService {
    Skill getSkillEntityById(Long id);
    List<SkillResponseDTO> getAllSkills();
}

