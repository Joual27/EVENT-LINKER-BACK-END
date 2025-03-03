package org.youcode.EventLinkerAPI.message;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;
import org.youcode.EventLinkerAPI.message.DTOs.SendMessageDTO;
import org.youcode.EventLinkerAPI.message.interfaces.MessageService;
import org.youcode.EventLinkerAPI.message.interfaces.ProducerService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.security.Principal;
import java.util.List;



@AllArgsConstructor
@Controller
public class MessageController {

    private final MessageService messageService;
    private final ProducerService producerService;


    @MessageMapping("/send")
    public void sendMessage(@Payload SendMessageDTO req , Principal principal){
        producerService.sendMessage(req , principal);
    }

    @GetMapping("/dms/{dmId}/undelivered")
    public ResponseEntity<SuccessDTO<List<MessageResponseDTO>>> getDmUndeliveredMessages(@PathVariable("dmId") Long dmId ){
        List<MessageResponseDTO> res = messageService.getDmUndeliveredMessages(dmId);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "messages retrieved successfully !" , res) , HttpStatus.OK);
    }
}
