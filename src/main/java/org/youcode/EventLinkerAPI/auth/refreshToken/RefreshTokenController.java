package org.youcode.EventLinkerAPI.auth.refreshToken;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.user.DTOs.AuthResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LogoutDTO;
import org.youcode.EventLinkerAPI.user.interfaces.AuthService;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class RefreshTokenController {
    private final AuthService authService;

    @GetMapping("refresh-token")
    public ResponseEntity<AuthResponseDTO> refreshToken(@CookieValue(name = "refreshToken") String refreshToken){
        AuthResponseDTO res = authService.refreshToken(refreshToken);
        return new ResponseEntity<>(res , HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@RequestBody @Valid LogoutDTO req){
        authService.logout(req);
        return new ResponseEntity<>("Logout out successfully" , HttpStatus.OK);
    }
}
