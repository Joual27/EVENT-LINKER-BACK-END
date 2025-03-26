package org.youcode.EventLinkerAPI.admin;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.admin.interfaces.AdminService;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/announcement/status")
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/{operation}/{id}")
    public ResponseEntity<SuccessDTO<AnnouncementResponseDTO>> updateAnnouncementStatus(@PathVariable("operation") String operation , @PathVariable("id") Long id){
        AnnouncementResponseDTO res = adminService.updateAnnouncementStatus(operation , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Status Updated Successfully !" , res ) , HttpStatus.OK);
    }
}
