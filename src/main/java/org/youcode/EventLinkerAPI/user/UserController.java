package org.youcode.EventLinkerAPI.user;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserResponseDTO;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<UserResponseDTO>> getUserDaTa(@PathVariable("id") Long id){
        UserResponseDTO res = userService.getUserData(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "user data retrieved successfully !" , res) , HttpStatus.OK);
    }
}
