package org.youcode.EventLinkerAPI.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDAO extends JpaRepository<Event, Long> {
    Page<Event> findByOrganizer_Id(Long organizerId , Pageable pageable);
}
