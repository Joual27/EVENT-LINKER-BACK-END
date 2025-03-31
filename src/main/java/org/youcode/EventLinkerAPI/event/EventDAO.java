package org.youcode.EventLinkerAPI.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.EventLinkerAPI.organizer.Organizer;

import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<Event, Long> {
    Page<Event> findByOrganizer_Id(Long organizerId , Pageable pageable);
    List<Event> findByOrganizer(Organizer organizer);
}
