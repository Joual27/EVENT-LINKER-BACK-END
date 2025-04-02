package org.youcode.EventLinkerAPI.announcement.interfaces;

import org.youcode.EventLinkerAPI.announcement.Announcement;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.CreateAnnouncementDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.UpdateAnnouncementDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;

import java.util.List;

public interface AnnouncementService {
    AnnouncementResponseDTO saveAnnouncement(CreateAnnouncementDTO data);
    AnnouncementResponseDTO updateAnnouncement(UpdateAnnouncementDTO data , Long id);
    PaginationDTO<List<AnnouncementResponseDTO>> getAllAnnouncements(int page , int size);
    PaginationDTO<List<AnnouncementResponseDTO>> getAllUsersAnnouncements(int page , int size);
    AnnouncementResponseDTO getAnnouncementById(Long id);
    AnnouncementResponseDTO deleteAnnouncement(Long id);
    Announcement getAnnouncementEntityById(Long id);
    AnnouncementResponseDTO updateAnnouncementStatus(String expectedOperation , Long id);
    PaginationDTO<List<AnnouncementResponseDTO>>filterAnnouncements(int page , int size, String term);

}