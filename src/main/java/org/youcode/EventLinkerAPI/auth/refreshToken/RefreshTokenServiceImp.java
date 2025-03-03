package org.youcode.EventLinkerAPI.auth.refreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.auth.refreshToken.interfaces.RefreshTokenService;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.user.UserDAO;
import java.time.Instant;
import java.util.UUID;


@Service
public class RefreshTokenServiceImp implements RefreshTokenService {
    private final RefreshTokenDAO refreshTokenDAO;
    private final UserDAO userDAO;
    private final long refreshTokenExpiration ;

    public RefreshTokenServiceImp (RefreshTokenDAO refreshTokenDAO , UserDAO userDAO ,@Value("${REFRESH_TOKEN_EXPIRATION_TIME}")  long refreshTokenExpiration){
        this.refreshTokenDAO = refreshTokenDAO;
        this.userDAO = userDAO;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    @Override
    public RefreshToken createRefreshToken(String email) {
        User user = userDAO.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No User Found With Given Email !"));
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expirationDate(Instant.now().plusMillis(refreshTokenExpiration))
                .build();
        return refreshTokenDAO.save(refreshToken);
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenDAO.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("No Refresh Token Found With Given DATA"));
    }

    @Override
    public boolean isTokenExpired(RefreshToken token) {
        if (token.getExpirationDate().compareTo(Instant.now()) < 0 ){
            refreshTokenDAO.delete(token);
            return true;
        }
        return false;
    }

    @Override
    public void deleteToken(RefreshToken token) {
        refreshTokenDAO.delete(token);
    }
}
