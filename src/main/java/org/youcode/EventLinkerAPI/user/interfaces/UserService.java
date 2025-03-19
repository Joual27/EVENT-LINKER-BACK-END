package org.youcode.EventLinkerAPI.user.interfaces;

import org.youcode.EventLinkerAPI.user.DTOs.UpdateProfileDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserStatsResponseDTO;
import org.youcode.EventLinkerAPI.user.User;

public interface UserService {
    User getUserEntityById(Long id );
    UserResponseDTO getUserData(Long id);
    UserStatsResponseDTO getUserStats(Long id);
    UserResponseDTO updateProfile(UpdateProfileDTO data);
}
