package org.youcode.EventLinkerAPI.skill;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.skill.DTOs.SkillResponseDTO;
import org.youcode.EventLinkerAPI.skill.interfaces.SkillService;
import org.youcode.EventLinkerAPI.skill.mapper.SkillMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class SkillsServiceImp implements SkillService {
    private final SkillDAO skillDAO;
    private final SkillMapper skillMapper;

    public Skill getSkillEntityById(Long id){
        return skillDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No SKill Found with given ID !"));
    }

    @Override
    public List<SkillResponseDTO> getAllSkills() {
        List<Skill> skills = skillDAO.findAll();
        return skills.stream()
                .map(skillMapper::toResponseDTO)
                .toList();
    }
}
