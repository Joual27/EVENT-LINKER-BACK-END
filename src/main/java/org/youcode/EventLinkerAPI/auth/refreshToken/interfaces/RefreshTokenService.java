package org.youcode.EventLinkerAPI.auth.refreshToken.interfaces;

import org.youcode.EventLinkerAPI.auth.refreshToken.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String email);
    RefreshToken findByToken(String token);
    boolean isTokenExpired(RefreshToken token);
    void deleteToken(RefreshToken token);
}
