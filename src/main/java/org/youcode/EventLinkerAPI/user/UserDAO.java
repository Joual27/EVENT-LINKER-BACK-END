package org.youcode.EventLinkerAPI.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDAO extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM DM d JOIN d.users u WHERE d.id = :dmId")
    Set<User> findParticipantsByDmId(@Param("dmId") Long dmId);
}

