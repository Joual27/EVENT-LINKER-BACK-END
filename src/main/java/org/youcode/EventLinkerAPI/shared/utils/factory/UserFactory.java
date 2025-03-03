package org.youcode.EventLinkerAPI.shared.utils.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.youcode.EventLinkerAPI.organizer.DTOs.OrganizerRegistrationDTO;
import org.youcode.EventLinkerAPI.organizer.Organizer;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.user.mapper.UserMapper;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerRegistrationDTO;
import org.youcode.EventLinkerAPI.worker.Worker;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Component
public class UserFactory {
    private final UserMapper userMapper;

    public User createUser(String userType , BaseRegistrationDTO dto){
        String type = userType.toUpperCase();
        switch (type){
            case "WORKER" :
                WorkerRegistrationDTO workerDTO = (WorkerRegistrationDTO) dto;
                Worker workerToCreate =  userMapper.toEntity(workerDTO);
                workerToCreate.setCreatedAt(LocalDateTime.now());
                return workerToCreate;
            case "ORGANIZER" :
                OrganizerRegistrationDTO organizerDTO = (OrganizerRegistrationDTO) dto;
                Organizer organizerToCreate =  userMapper.toEntity(organizerDTO);
                organizerToCreate.setCreatedAt(LocalDateTime.now());
                return organizerToCreate;
            default:
                throw new IllegalArgumentException("Invalid User Type !");
         }
    }
}
