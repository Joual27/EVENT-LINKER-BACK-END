package org.youcode.EventLinkerAPI.worker.interfaces;

import org.youcode.EventLinkerAPI.worker.DTOs.UpdateWorkerSkillsDTO;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerSkillsResponseDTO;
import org.youcode.EventLinkerAPI.worker.Worker;


public interface WorkerService {
    WorkerSkillsResponseDTO removeWorkerSkill(Long workerId , Long skillId);
    WorkerSkillsResponseDTO updateWorkerSkills(UpdateWorkerSkillsDTO data , Long workerId);
    Worker getWorkerEntityById(Long id);
    Worker updateWorkerBalance(Worker worker , double amount);
    Worker updateWorkerStripeId(Worker worker , String stripeId);
    Worker postWithdrawalBalanceUpdate(Worker worker , Long amount);
}
