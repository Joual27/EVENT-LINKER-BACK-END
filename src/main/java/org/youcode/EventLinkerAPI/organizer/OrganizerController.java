package org.youcode.EventLinkerAPI.organizer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.organizer.interfaces.OrganizerService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/organizer/applications")
public class OrganizerController {
    private final OrganizerService organizerService;

    @PatchMapping("/{action}/{id}")
    public ResponseEntity<SuccessDTO<ApplicationResponseDTO>> updateApplicationStatus(@PathVariable("action") String action , @PathVariable("id") Long id){
        ApplicationResponseDTO res = organizerService.updateApplicationStatus(action, id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , " Application status updated successfully " , res) , HttpStatus.OK);
    }
}
