package org.youcode.EventLinkerAPI.DM;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.DM.DTOs.CreateDmDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.DM.DTOs.DmWithLastMessageDTO;
import org.youcode.EventLinkerAPI.DM.interfaces.DMService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/DMs")
public class DMController {
    private final DMService dmService;

    @PostMapping
    public ResponseEntity<SuccessDTO<DmResponseDTO>> createDM(@RequestBody @Valid CreateDmDTO req){
        DmResponseDTO res = dmService.createOrFindDM(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "DM CREATED SUCCESSFULLY ! " ,res ) , HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<SuccessDTO<List<DmWithLastMessageDTO> >> getAllDMs(){
        List<DmWithLastMessageDTO>  res = dmService.getAllUserDMs();
        return new ResponseEntity<>(new SuccessDTO<>("success" , "DMs retrieved successfully !" , res) , HttpStatus.OK);
    }

}
