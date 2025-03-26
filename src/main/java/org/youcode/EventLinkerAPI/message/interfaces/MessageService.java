package org.youcode.EventLinkerAPI.message.interfaces;

import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;
import org.youcode.EventLinkerAPI.message.Message;

import java.util.List;

public interface MessageService {

    List<MessageResponseDTO> getDmUndeliveredMessages(Long dmId);
    Message getMessageEntityById(Long id );
}
