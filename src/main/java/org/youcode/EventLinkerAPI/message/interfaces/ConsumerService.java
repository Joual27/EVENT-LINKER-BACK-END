package org.youcode.EventLinkerAPI.message.interfaces;

import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;

public interface ConsumerService {
    void processMessage(MessageResponseDTO message);
}
