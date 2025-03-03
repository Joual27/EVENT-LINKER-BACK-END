package org.youcode.EventLinkerAPI.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageDAO extends JpaRepository<Message , Long> {
    List<Message> findByDmAndDeliveredFalseAndUserNot(DM dm, User user);
    @Query("SELECT m FROM Message m " +
            "LEFT JOIN FETCH m.dm d " +
            "LEFT JOIN FETCH d.users " +
            "WHERE m.id = :id")
    Optional<Message> findByIdWithRelations(@Param("id") Long id);
}
