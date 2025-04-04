package org.youcode.EventLinkerAPI.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;

@Repository
public interface ApplicationDAO extends JpaRepository<Application , Long> {
    Page<Application> findAllByApplicant_Id(Pageable pageable , Long applicantId);

    Page<Application> findAllByAnnouncement_IdAndStatus(Pageable pageable , Long announcementId , ApplicationStatus status);
    @Modifying
    @Query("DELETE FROM Announcement a WHERE a.id = :id")
    void deleteById(@Param("id") Long id);
}
