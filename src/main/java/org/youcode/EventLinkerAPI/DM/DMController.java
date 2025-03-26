package org.youcode.EventLinkerAPI.DM;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.DM.DTOs.CreateDmDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.DM.interfaces.DMService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/organizer/DMs")
public class DMController {
    private final DMService dmService;

    @PostMapping
    public ResponseEntity<SuccessDTO<DmResponseDTO>> createDM(@RequestBody @Valid CreateDmDTO req){
        DmResponseDTO res = dmService.saveDM(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "DM CREATED SUCCESSFULLY ! " ,res ) , HttpStatus.CREATED);
    }

}
