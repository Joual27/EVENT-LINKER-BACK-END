package org.youcode.EventLinkerAPI.DM.DTOs;

import org.youcode.EventLinkerAPI.message.DTOs.EmbeddedMessageDTO;

public record DmWithLastMessageDTO(
        DmResponseDTO dm,
        EmbeddedMessageDTO lastMessage
) {}
