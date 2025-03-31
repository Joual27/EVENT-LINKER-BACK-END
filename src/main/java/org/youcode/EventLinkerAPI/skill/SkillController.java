package org.youcode.EventLinkerAPI.skill;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;
import org.youcode.EventLinkerAPI.skill.DTOs.SkillResponseDTO;
import org.youcode.EventLinkerAPI.skill.interfaces.SkillService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/skills")
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<SuccessDTO<List<SkillResponseDTO>>> getAllSkills(){
        List<SkillResponseDTO> res = skillService.getAllSkills();
        return new ResponseEntity<>(new SuccessDTO<>("success" , "skills retrieved successfully !" , res) , HttpStatus.OK);
    }
}
