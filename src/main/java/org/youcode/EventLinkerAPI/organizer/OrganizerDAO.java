package org.youcode.EventLinkerAPI.organizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerDAO extends JpaRepository<Organizer , Long> {

}
