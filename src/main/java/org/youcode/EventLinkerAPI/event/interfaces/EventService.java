package org.youcode.EventLinkerAPI.event.interfaces;

import org.youcode.EventLinkerAPI.event.DTOs.CreateEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EventResponseDTO;
import org.youcode.EventLinkerAPI.event.DTOs.UpdateEventDTO;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;

import java.util.List;

public interface EventService {
    EventResponseDTO saveEvent(CreateEventDTO data );
    EventResponseDTO updateEvent(UpdateEventDTO data , Long id);
    PaginationDTO<List<EventResponseDTO>> getAllEvents(int page , int size);
    EventResponseDTO getEventById(Long id);
    EventResponseDTO deleteEvent(Long id);
    Event getEventEntityById(Long id);
    List<EventResponseDTO> getAllEventsNoPagination();
}
