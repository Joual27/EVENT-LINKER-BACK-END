package org.youcode.EventLinkerAPI.user.DTOs;

public record AuthResponseDTO(Long id , AccessTokenResponseDTO tokens , String role) {
}
