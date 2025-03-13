package org.youcode.EventLinkerAPI.user;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.auth.refreshToken.interfaces.RefreshTokenService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.user.DTOs.AccessTokenResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LoginDTO;
import org.youcode.EventLinkerAPI.user.DTOs.AuthResponseDTO;
import org.youcode.EventLinkerAPI.user.interfaces.AuthService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/public/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register/{userType}")
    public ResponseEntity<SuccessDTO<AuthResponseDTO>> register(
            @PathVariable String userType,
            @Valid BaseRegistrationDTO req,
            HttpServletResponse response) {
        AuthResponseDTO res = authService.createUser(userType, req);
        ResponseCookie refreshTokenCookie = refreshTokenService.createRefreshTokenCookie(res.tokens().refreshToken());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return new ResponseEntity<>(new SuccessDTO<>("Success", res.role() + " created successfully", res), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessDTO<AuthResponseDTO>> authenticate(
            @RequestBody @Valid LoginDTO req,
            HttpServletResponse response) {
        AuthResponseDTO res = authService.authenticate(req);

        ResponseCookie refreshTokenCookie = refreshTokenService.createRefreshTokenCookie(res.tokens().refreshToken());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return new ResponseEntity<>(new SuccessDTO<>("Success", "Authenticated successfully!", res), HttpStatus.OK);
    }

}
