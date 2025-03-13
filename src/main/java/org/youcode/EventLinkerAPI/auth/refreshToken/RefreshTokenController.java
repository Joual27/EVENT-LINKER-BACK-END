package org.youcode.EventLinkerAPI.auth.refreshToken;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.auth.refreshToken.interfaces.RefreshTokenService;
import org.youcode.EventLinkerAPI.user.DTOs.AuthResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LogoutDTO;
import org.youcode.EventLinkerAPI.user.interfaces.AuthService;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class RefreshTokenController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("refresh-token")
    public ResponseEntity<AuthResponseDTO> refreshToken(@CookieValue(name = "refreshToken") String refreshToken){
        AuthResponseDTO res = authService.refreshToken(refreshToken);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenService.createRefreshTokenCookie(refreshToken).toString())
                .body(res);
    }
    @PostMapping("logout")
    public ResponseEntity<String> logout(@RequestBody @Valid LogoutDTO req){
        authService.logout(req);
        return new ResponseEntity<>("Logout out successfully" , HttpStatus.OK);
    }
}
