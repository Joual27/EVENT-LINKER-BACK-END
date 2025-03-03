package org.youcode.EventLinkerAPI.user.interfaces;

import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.user.DTOs.AccessTokenResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LoginDTO;
import org.youcode.EventLinkerAPI.user.DTOs.AuthResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LogoutDTO;

public interface AuthService {
    AuthResponseDTO createUser(String userType , BaseRegistrationDTO data);
    AuthResponseDTO authenticate(LoginDTO credentials);
    AuthResponseDTO refreshToken(String token);
    void logout(LogoutDTO data);
}
