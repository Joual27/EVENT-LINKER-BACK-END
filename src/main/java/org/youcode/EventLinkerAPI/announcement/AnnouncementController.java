package org.youcode.EventLinkerAPI.announcement;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.CreateAnnouncementDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.UpdateAnnouncementDTO;
import org.youcode.EventLinkerAPI.announcement.interfaces.AnnouncementService;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/organizer/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<SuccessDTO<AnnouncementResponseDTO>> createAnnouncement(@RequestBody @Valid CreateAnnouncementDTO req){
        AnnouncementResponseDTO res = announcementService.saveAnnouncement(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Announcement Created Successfully !" , res) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<AnnouncementResponseDTO>> updateAnnouncement(@RequestBody @Valid UpdateAnnouncementDTO req , @PathVariable("id") Long id){
        AnnouncementResponseDTO res = announcementService.updateAnnouncement(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Announcement Updated Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<PaginationDTO<List<AnnouncementResponseDTO>>>> getAuthenticatedOrganizerAnnouncements(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "7") int size){
        PaginationDTO<List<AnnouncementResponseDTO>> res = announcementService.getAllAnnouncements(page, size);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Announcements of page " + page +" Retrieved Successfully !" , res) , HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<AnnouncementResponseDTO>> getAnnouncementById(@PathVariable("id") Long id){
        AnnouncementResponseDTO res = announcementService.getAnnouncementById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Announcement Retrieved Successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<AnnouncementResponseDTO>> deleteAnnouncement(@PathVariable("id") Long id){
        AnnouncementResponseDTO res = announcementService.deleteAnnouncement(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Announcement Deleted Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<SuccessDTO<PaginationDTO<List<ApplicationResponseDTO>>>> getAnnouncementApplications(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "7") int size , @PathVariable("id") Long id){
        PaginationDTO<List<ApplicationResponseDTO>> res = applicationService.getAnnouncementApplications(page, size, id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Applications of page " + page +" Retrieved Successfully !" , res) , HttpStatus.OK);
    }
}
