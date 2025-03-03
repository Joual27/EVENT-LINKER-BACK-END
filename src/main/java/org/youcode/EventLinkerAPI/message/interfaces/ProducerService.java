package org.youcode.EventLinkerAPI.message.interfaces;

import org.youcode.EventLinkerAPI.message.DTOs.SendMessageDTO;

import java.security.Principal;

public interface ProducerService {
    void sendMessage(SendMessageDTO data , Principal principal);
}
