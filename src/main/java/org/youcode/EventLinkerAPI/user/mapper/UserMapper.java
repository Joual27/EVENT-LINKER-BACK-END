package org.youcode.EventLinkerAPI.user.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.organizer.DTOs.OrganizerRegistrationDTO;
import org.youcode.EventLinkerAPI.organizer.Organizer;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerRegistrationDTO;
import org.youcode.EventLinkerAPI.worker.Worker;
import java.time.LocalDateTime;


@Mapper(componentModel = "spring")
public interface UserMapper extends BaseEmbeddedMapper<User , EmbeddedUserDTO> {
    public abstract Organizer toEntity(OrganizerRegistrationDTO dto);
    public abstract Worker toEntity(WorkerRegistrationDTO dto);
}
