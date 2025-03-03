package org.youcode.EventLinkerAPI.user;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.auth.refreshToken.RefreshToken;
import org.youcode.EventLinkerAPI.auth.refreshToken.interfaces.RefreshTokenService;
import org.youcode.EventLinkerAPI.exceptions.TokenExpiredException;
import org.youcode.EventLinkerAPI.shared.utils.factory.UserFactory;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.shared.utils.security.JwtService;
import org.youcode.EventLinkerAPI.user.DTOs.AccessTokenResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LoginDTO;
import org.youcode.EventLinkerAPI.user.DTOs.AuthResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.LogoutDTO;
import org.youcode.EventLinkerAPI.user.interfaces.AuthService;


@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final UserDAO userDAO;
    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthResponseDTO createUser(String userType , BaseRegistrationDTO data) {
        User userToCreate = userFactory.createUser(userType , data);
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        User createdUser = userDAO.save(userToCreate);
        String accessToken = jwtService.generateToken(createdUser);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(createdUser.getEmail());
        return new AuthResponseDTO(createdUser.getId() , new AccessTokenResponseDTO(accessToken , refreshToken.getToken()   ) , createdUser.getUserRole());
    }

    @Override
    public AuthResponseDTO authenticate(LoginDTO credentials) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.email() , credentials.password());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        if (auth.isAuthenticated()){
            User signedInUser = (User) auth.getPrincipal();
            String accessToken = jwtService.generateToken(signedInUser);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(signedInUser.getUsername());
            return new AuthResponseDTO(signedInUser.getId() , new AccessTokenResponseDTO(accessToken , refreshToken.getToken()) , signedInUser.getUserRole());
        }
        else {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }

    @Override
    public AuthResponseDTO refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token);
        if (refreshTokenService.isTokenExpired(refreshToken)){
            throw new TokenExpiredException("THE USED REFRESH TOKEN HAS EXPIRED ! TRY SIGNING IN AGAIN !");
        }
        User userData = refreshToken.getUser();
        String accessToken = jwtService.generateToken(userData);
        return new AuthResponseDTO(userData.getId() , new AccessTokenResponseDTO(accessToken , token) , userData.getUserRole());
    }

    @Transactional
    @Override
    public void logout(LogoutDTO data) {
        RefreshToken refreshToken = refreshTokenService.findByToken(data.token());
        refreshTokenService.deleteToken(refreshToken);
    }

}
