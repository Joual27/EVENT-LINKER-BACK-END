package org.youcode.EventLinkerAPI.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.admin.interfaces.AdminService;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;
import org.youcode.EventLinkerAPI.announcement.interfaces.AnnouncementService;

@AllArgsConstructor
@Service
public class AdminServiceImp implements AdminService {
    private final AnnouncementService announcementService;

    @Override
    public AnnouncementResponseDTO updateAnnouncementStatus(String expectedOperation , Long announcementId) {
        return announcementService.updateAnnouncementStatus(expectedOperation , announcementId);
    }
}
