package org.youcode.EventLinkerAPI.event;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.EventLinkerAPI.event.DTOs.CreateEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EventResponseDTO;
import org.youcode.EventLinkerAPI.event.DTOs.UpdateEventDTO;
import org.youcode.EventLinkerAPI.event.interfaces.EventService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/organizer/events")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<SuccessDTO<EventResponseDTO>> createEvent(@ModelAttribute @Valid CreateEventDTO req){
        EventResponseDTO res = eventService.saveEvent(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Event Created Successfully !" , res) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<EventResponseDTO>> updateEvent(@ModelAttribute @Valid UpdateEventDTO req , @PathVariable("id") Long id){
        EventResponseDTO res = eventService.updateEvent(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Event Updated Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<PaginationDTO<List<EventResponseDTO>>>> getAllEvents(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "2") int size){
        PaginationDTO<List<EventResponseDTO>> res = eventService.getAllEvents(page,size);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Event page " + page + " Retrieved Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<EventResponseDTO>> getEventById(@PathVariable("id") Long id){
        EventResponseDTO res = eventService.getEventById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Event Retrieved Successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<EventResponseDTO>> deleteEvent(@PathVariable("id") Long id){
        EventResponseDTO res = eventService.deleteEvent(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Event Deleted Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("/no-pagination")
    public ResponseEntity<SuccessDTO<List<EventResponseDTO>>> getAllEventsWithoutPagination(){
        List<EventResponseDTO> res = eventService.getAllEventsNoPagination();
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Events Retrieved Successfully !" , res) , HttpStatus.OK);
    }

}
