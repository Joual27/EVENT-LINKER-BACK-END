package org.youcode.EventLinkerAPI.admin.interfaces;

import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;

public interface AdminService {
    AnnouncementResponseDTO updateAnnouncementStatus(String expectedOperation , Long announcementId);
}
