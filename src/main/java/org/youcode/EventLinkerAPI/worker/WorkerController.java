package org.youcode.EventLinkerAPI.worker;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;
import org.youcode.EventLinkerAPI.worker.DTOs.UpdateWorkerSkillsDTO;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerSkillsResponseDTO;
import org.youcode.EventLinkerAPI.worker.interfaces.WorkerService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/worker/skills")
public class WorkerController {
    private final WorkerService workerService;

    @PutMapping("/{workerId}")
    public ResponseEntity<SuccessDTO<WorkerSkillsResponseDTO>> updateWorkerSkills(@RequestBody @Valid UpdateWorkerSkillsDTO req , @PathVariable("workerId") Long workerId){
        WorkerSkillsResponseDTO res = workerService.updateWorkerSkills(req , workerId);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Worker SKills updated Successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{workerId}/{skillId}")
    public ResponseEntity<SuccessDTO<WorkerSkillsResponseDTO>> deleteWorkerSkill(@PathVariable("workerId") Long workerId , @PathVariable("skillId") Long skillId){
        WorkerSkillsResponseDTO res = workerService.removeWorkerSkill(workerId , skillId);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Worker Skill Deleted Successfully !" , res) , HttpStatus.OK);
    }
}
