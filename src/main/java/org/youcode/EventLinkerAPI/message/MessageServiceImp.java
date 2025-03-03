package org.youcode.EventLinkerAPI.message;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.DM.interfaces.DMService;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;
import org.youcode.EventLinkerAPI.message.interfaces.MessageService;
import org.youcode.EventLinkerAPI.message.mapper.MessageMapper;
import org.youcode.EventLinkerAPI.user.User;

import java.util.List;

@AllArgsConstructor
@Service
public class MessageServiceImp implements MessageService {

    private final DMService dmService;
    private final MessageDAO messageDAO;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageResponseDTO> getDmUndeliveredMessages(Long dmId){
        DM existingDm = dmService.getDMEntityById(dmId);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!isUserDM(existingDm , authenticatedUser)){
            throw new AccessDeniedException("You only see messages of your dms ! ");
        }
        List<Message> undeliveredMessages = messageDAO.findByDmAndDeliveredFalseAndUserNot(existingDm, authenticatedUser);
        return undeliveredMessages.stream().map(messageMapper::toResponseDTO).toList();
    }

    private boolean isUserDM(DM dm , User user){
        return dm.getUsers().contains(user);
    }

    @Override
    public Message getMessageEntityById(Long id ){
        return messageDAO.findByIdWithRelations(id)
                .orElseThrow(() -> new EntityNotFoundException("NO MESSAGE FOUND WITH GIVEN ID ! "));
    }
}
