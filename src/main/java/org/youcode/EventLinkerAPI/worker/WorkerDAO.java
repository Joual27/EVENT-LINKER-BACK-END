package org.youcode.EventLinkerAPI.worker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerDAO extends JpaRepository<Worker , Long> {
}
