package org.youcode.EventLinkerAPI.message;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.DM.interfaces.DMService;
import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;
import org.youcode.EventLinkerAPI.message.DTOs.SendMessageDTO;
import org.youcode.EventLinkerAPI.message.interfaces.ProducerService;
import org.youcode.EventLinkerAPI.message.mapper.MessageMapper;
import org.youcode.EventLinkerAPI.user.User;

import java.security.Principal;
import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class ProducerServiceImp implements ProducerService {

    private final MessageDAO messageDAO;
    private final DMService dmService;
    private final MessageMapper messageMapper;
    private final KafkaTemplate<String, MessageResponseDTO> kafkaTemplate;

    @Transactional
    @Override
    public void sendMessage(SendMessageDTO data , Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();
        DM existingDm = dmService.getDMEntityById(data.dmId());
        existingDm.setUsers(dmService.getDmParticipants(existingDm.getId()));
        if (!isUserDM(existingDm , user)){
            throw new AccessDeniedException("You Don't belong to this DM !");
        }
        Message messageToSend = new Message();
        Message messageWithData = fillMessageDefaultData(messageToSend , user , existingDm , data.content());
        Message createdMessage = messageDAO.save(messageWithData);
        MessageResponseDTO res = messageMapper.toResponseDTO(createdMessage);
        System.out.println(res);
        kafkaTemplate.send("direct-messages" , messageToSend.getDm().getId().toString() , res);

    }

    private Message fillMessageDefaultData(Message message , User user , DM dm , String content){
        message.setSentAt(LocalDateTime.now());
        message.setUser(user);
        message.setDelivered(false);
        message.setDm(dm);
        message.setContent(content);
        return message;
    }
    private boolean isUserDM(DM dm, User user) {
        return dm.getUsers()
                .stream()
                .anyMatch(u -> u.getId().equals(user.getId()));
    }
}
