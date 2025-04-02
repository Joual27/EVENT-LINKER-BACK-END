package org.youcode.EventLinkerAPI.DM;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DMDAO extends JpaRepository<DM , Long> {
    @EntityGraph(attributePaths = {"users"})
    Optional<DM> findById(@Param("dmId") Long dmId);
    @Query("SELECT DISTINCT d FROM DM d JOIN d.users u W" +
            "HERE u.id = :userId")
    @EntityGraph(attributePaths = {"users", "messages"})
    List<DM> findAllByUserId(@Param("userId") Long userId);


}
