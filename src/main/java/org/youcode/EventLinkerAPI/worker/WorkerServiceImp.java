package org.youcode.EventLinkerAPI.worker;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.skill.Skill;
import org.youcode.EventLinkerAPI.skill.SkillService;
import org.youcode.EventLinkerAPI.worker.DTOs.EmbeddedWorkerSkillsCreationalDTO;
import org.youcode.EventLinkerAPI.worker.DTOs.UpdateWorkerSkillsDTO;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerSkillsResponseDTO;
import org.youcode.EventLinkerAPI.worker.interfaces.WorkerService;
import org.youcode.EventLinkerAPI.worker.mapper.WorkerMapper;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class WorkerServiceImp implements WorkerService {
    private final WorkerDAO workerDAO;
    private final SkillService skillService;
    private final WorkerMapper workerMapper;

    @Override
    public WorkerSkillsResponseDTO removeWorkerSkill(Long workerId , Long skillId){
        Worker existingWorker = getWorkerEntityById(workerId);
        Skill existingSkill = skillService.getSkillEntityById(skillId);
        verifyWorkerIdentity(existingWorker , "You can only delete your own skills !");
        List<Skill> workerSkillsWithDeletedTarget = excludeWorkerSkill(existingWorker , skillId);
        existingWorker.setSkills(workerSkillsWithDeletedTarget);
        workerDAO.save(existingWorker);
        return workerMapper.toResponseDTO(existingWorker);
    }

    @Override
    public WorkerSkillsResponseDTO updateWorkerSkills(UpdateWorkerSkillsDTO data , Long workerId) {
        Worker existingWorker = getWorkerEntityById(workerId);
        verifyWorkerIdentity(existingWorker , "You can only update your own skills !");
        validateAndMapSkillsToWorker(existingWorker , data.skills());
        Worker updatedWorker = workerDAO.save(existingWorker);
        return workerMapper.toResponseDTO(updatedWorker);
    }

    @Override
    public Worker getWorkerEntityById(Long id) {
        return workerDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO WORKER FOUND WITH GIVEN ID !"));
    }

    @Override
    public Worker updateWorkerBalance(Worker worker, double amount) {
        double newBalance = worker.getBalance() + amount;
        worker.setBalance(newBalance);
        return workerDAO.save(worker);
    }

    @Override
    public Worker updateWorkerStripeId( Worker worker , String stripeId){
        worker.setStripeAccountId(stripeId);
        return workerDAO.save(worker);
    }

    @Override
    public Worker postWithdrawalBalanceUpdate(Worker worker , Long amount){
        double newBalance = worker.getBalance() - amount;
        worker.setBalance(newBalance);
        return workerDAO.save(worker);
    }

    private void verifyWorkerIdentity(Worker worker ,String message){
        Worker signedInWorker = (Worker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!worker.getId().equals(signedInWorker.getId())){
            throw new AccessDeniedException(message);
        }
    }

    private void validateAndMapSkillsToWorker(Worker worker , List<EmbeddedWorkerSkillsCreationalDTO> skills){
          List<Skill> existingSkills =  new ArrayList<>(skills.stream()
                  .map(skill -> skillService.getSkillEntityById(skill.skillId()))
                  .toList());
          worker.setSkills(existingSkills);
    }

    private List<Skill> excludeWorkerSkill(Worker worker , Long skillId){
        return new ArrayList<>(worker.getSkills().stream()
                .filter(skill -> !skill.getId().equals(skillId))
                .toList());
    }
}
