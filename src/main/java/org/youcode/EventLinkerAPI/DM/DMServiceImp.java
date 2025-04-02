package org.youcode.EventLinkerAPI.DM;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.DM.DTOs.CreateDmDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmWithLastMessageDTO;
import org.youcode.EventLinkerAPI.DM.interfaces.DMService;
import org.youcode.EventLinkerAPI.DM.mapper.DMMapper;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.message.DTOs.EmbeddedMessageDTO;
import org.youcode.EventLinkerAPI.message.MessageDAO;
import org.youcode.EventLinkerAPI.message.mapper.MessageMapper;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.user.UserDAO;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;

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
    private final MessageDAO messageDAO;
    private final MessageMapper messageMapper;

    @Transactional
    @Override
    public DM getDMEntityById(Long id) {
        return dmDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO DM FOUND WITH GIVEN ID ! "));
    }

    @Override
    @Transactional
    public DmResponseDTO createOrFindDM(CreateDmDTO data) {
        Set<User> participants = validateDmParticipants(data.userIds());

        DM existingDm = findExistingDM(participants);
        if (existingDm != null) {
            return dmMapper.toResponseDTO(existingDm);
        }

        DM newDm = new DM();
        newDm.setUsers(participants);
        DM createdDm = dmDao.save(newDm);
        return dmMapper.toResponseDTO(createdDm);
    }

    private DM findExistingDM(Set<User> participants) {
        List<Long> participantIds = participants.stream()
                .map(User::getId)
                .sorted()
                .toList();
        return dmDao.findAll().stream()
                .filter(dm -> {
                    Set<User> dmUsers = dm.getUsers();
                    List<Long> dmUserIds = dmUsers.stream()
                            .map(User::getId)
                            .sorted()
                            .toList();
                    return dmUserIds.equals(participantIds);
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<User> getDmParticipants(Long dmId){
        return userDAO.findParticipantsByDmId(dmId);
    }

    @Override
    public List<DmWithLastMessageDTO> getAllUserDMs() {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<DM> userDMs = dmDao.findAllByUserId(authenticatedUser.getId());

        return userDMs.stream()
                .map(dm -> {
                    DmResponseDTO dmDto = dmMapper.toResponseDTO(dm);
                    EmbeddedMessageDTO lastMessage = getLastMessageForDM(dm.getId());
                    return new DmWithLastMessageDTO(
                            dmDto,
                            lastMessage
                    );
                })
                .toList();
    }

    private EmbeddedMessageDTO getLastMessageForDM(Long dmId) {
        return messageMapper.toEmbeddedDTO(messageDAO.findTopByDmIdOrderBySentAtDesc(dmId).orElse(null));
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
