package org.youcode.EventLinkerAPI.user;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UpdateProfileDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserStatsResponseDTO;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<UserResponseDTO>> getUserDaTa(@PathVariable("id") Long id){
        UserResponseDTO res = userService.getUserData(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "user data retrieved successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<SuccessDTO<UserStatsResponseDTO>>getUserStats(@PathVariable("id") Long id){
        UserStatsResponseDTO res = userService.getUserStats(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "user stats retrieved successfully !" , res) , HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<SuccessDTO<UserResponseDTO>> updateProfile(@Valid @ModelAttribute UpdateProfileDTO data){
        UserResponseDTO res = userService.updateProfile(data);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "user profile successfully !" , res) , HttpStatus.OK);
    }
}
