package org.youcode.EventLinkerAPI.auth.refreshToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenDAO extends CrudRepository<RefreshToken , Long> {
    Optional<RefreshToken> findByToken(String token);
}
