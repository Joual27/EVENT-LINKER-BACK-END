package org.youcode.EventLinkerAPI.announcement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;

@Repository
public interface AnnouncementDAO extends JpaRepository<Announcement , Long> , JpaSpecificationExecutor<Announcement> {
    Page<Announcement> findByEvent_Organizer_Id(Pageable pageable , Long organizerId);
}
