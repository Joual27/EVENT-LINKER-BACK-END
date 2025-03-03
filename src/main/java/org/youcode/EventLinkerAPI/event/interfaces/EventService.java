package org.youcode.EventLinkerAPI.event.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.EventLinkerAPI.event.DTOs.CreateAndUpdateEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EventResponseDTO;
import org.youcode.EventLinkerAPI.event.Event;

import java.util.List;

public interface EventService {
    EventResponseDTO saveEvent(CreateAndUpdateEventDTO data );
    EventResponseDTO updateEvent(CreateAndUpdateEventDTO data , Long id);
    Page<EventResponseDTO> getAllEvents(int page , int size);
    EventResponseDTO getEventById(Long id);
    EventResponseDTO deleteEvent(Long id);
    Event getEventEntityById(Long id);
}
