package org.youcode.EventLinkerAPI.DM;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.DM.DTOs.CreateDmDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.DM.interfaces.DMService;
import org.youcode.EventLinkerAPI.DM.mapper.DMMapper;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.user.UserDAO;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DMServiceImp implements DMService {

    private final DMDAO dmDao;
    private final UserDAO userDAO;
    private final UserService userService;
    private final DMMapper dmMapper;

    @Transactional
    @Override
    public DM getDMEntityById(Long id) {
        return dmDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO DM FOUND WITH GIVEN ID ! "));
    }

    @Override
    public DmResponseDTO saveDM(CreateDmDTO data){
        Set<User> dmParticipants = validateDmParticipants(data.userIds());
        DM dm = new DM();
        dm.setUsers(dmParticipants);
        DM createdDm = dmDao.save(dm);
        return dmMapper.toResponseDTO(createdDm);
    }

    @Override
    public Set<User> getDmParticipants(Long dmId){
        return userDAO.findParticipantsByDmId(dmId);
    }

    private Set<User> validateDmParticipants(List<Long> ids){
        Set<User> res = new HashSet<>();
        for (Long id : ids){
            User existingUser = userService.getUserEntityById(id);
            res.add(existingUser);
        }
        return res;
    }

}
