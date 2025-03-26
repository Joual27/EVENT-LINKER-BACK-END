package org.youcode.EventLinkerAPI.application;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.application.DTOs.CreateApplicationDTO;
import org.youcode.EventLinkerAPI.application.DTOs.UpdateApplicationDTO;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/worker/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<SuccessDTO<ApplicationResponseDTO>> createApplication(@RequestBody @Valid CreateApplicationDTO req){
        ApplicationResponseDTO res = applicationService.saveApplication(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Application Made Successfully !" , res) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<ApplicationResponseDTO>> updateApplication(@RequestBody @Valid UpdateApplicationDTO req , @PathVariable("id") Long id){
        ApplicationResponseDTO res = applicationService.updateApplication(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Application Updated Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<ApplicationResponseDTO>> getApplicationById(@PathVariable("id") Long id){
        ApplicationResponseDTO res = applicationService.getApplicationById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Application Retrieved Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<ApplicationResponseDTO>>> getAllApplications(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "2") int size){
        Page<ApplicationResponseDTO> res = applicationService.getAllApplications(page, size);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Applications Of Page " +page +" Retrieved Successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<ApplicationResponseDTO>> deleteApplication(@PathVariable("id") Long id){
        ApplicationResponseDTO res = applicationService.deleteApplication(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Application Deleted Successfully !" , res) , HttpStatus.OK);
    }


}
