package org.youcode.EventLinkerAPI.announcement;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;
import org.youcode.EventLinkerAPI.announcement.interfaces.AnnouncementService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/worker/announcements")
@RestController
public class WorkerAnnouncementsController {
    private final AnnouncementService announcementService;

    @GetMapping()
    public ResponseEntity<SuccessDTO<PaginationDTO<List<AnnouncementResponseDTO>>>> getAllAnnouncements(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "7") int size) {
        PaginationDTO<List<AnnouncementResponseDTO>> res = announcementService.getAllUsersAnnouncements(page, size);
        return new ResponseEntity<>(new SuccessDTO<>("success", "Announcements of page " + page + " Retrieved Successfully !", res), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<SuccessDTO<PaginationDTO<List<AnnouncementResponseDTO>>>> filterAnnouncements(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "7") int size, @RequestParam() String term) {
        PaginationDTO<List<AnnouncementResponseDTO>> res = announcementService.filterAnnouncements(page, size, term);
        return new ResponseEntity<>(new SuccessDTO<>("success", "Announcements of page " + page + " Retrieved Successfully !", res), HttpStatus.OK);
    }
}