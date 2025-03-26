package org.youcode.EventLinkerAPI.DM.interfaces;

import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.DM.DTOs.CreateDmDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.user.User;

import java.util.List;
import java.util.Set;

public interface DMService {
    DM getDMEntityById(Long id);
    DmResponseDTO saveDM(CreateDmDTO data);
    Set<User> getDmParticipants(Long dmId);
}
