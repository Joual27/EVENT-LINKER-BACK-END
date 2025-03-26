package org.youcode.EventLinkerAPI.worker.DTOs;
import org.youcode.EventLinkerAPI.skill.DTOs.EmbeddedSkillDTO;
import java.util.List;

public record WorkerSkillsResponseDTO(String username , String email , boolean isOrganization , double balance , List<EmbeddedSkillDTO> skills) {
}
