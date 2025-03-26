package org.youcode.EventLinkerAPI.skill;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;

@AllArgsConstructor
@Service
public class SkillsServiceImp implements SkillService{
    private final SkillDAO skillDAO;

    public Skill getSkillEntityById(Long id){
        return skillDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No SKill Found with given ID !"));
    }
}
