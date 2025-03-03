package org.youcode.EventLinkerAPI.DM.DTOs;

import org.youcode.EventLinkerAPI.user.DTOs.EmbeddedUserDTO;

import java.util.List;

public record DmResponseDTO(Long id , List<EmbeddedUserDTO> users) {
}
