package org.youcode.EventLinkerAPI.event;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.event.DTOs.CreateEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EventResponseDTO;
import org.youcode.EventLinkerAPI.event.DTOs.UpdateEventDTO;
import org.youcode.EventLinkerAPI.event.interfaces.EventService;
import org.youcode.EventLinkerAPI.event.mapper.EventMapper;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.organizer.Organizer;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.FileUploadService;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {
    private final EventDAO eventDAO;
    private final EventMapper eventMapper;
    private final FileUploadService fileUploadService;

    @Override
    public EventResponseDTO saveEvent(CreateEventDTO data) {
        if (!isValidDate(data.date())){
            throw new IllegalArgumentException("Event date must be in the future !");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Organizer eventOrganizer = (Organizer) authentication.getPrincipal();
        String imgUrl = fileUploadService.uploadImage(data.img());
        Event event = eventMapper.toEntity(data);
        event.setOrganizer(eventOrganizer);
        event.setImgUrl(imgUrl);
        Event createdEvent = eventDAO.save(event);
        return eventMapper.toResponseDTO(createdEvent);
    }

    @Override
    public EventResponseDTO updateEvent(UpdateEventDTO data , Long id) {
        Organizer eventOrganizer = (Organizer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Event eventToUpdate = getEventEntityById(id);
        assertIsOrganizerEvent(eventToUpdate , "You can't Update an error that ain't Yours !");
        if (!isValidDate(data.date())){
            throw new IllegalArgumentException("Event date must be in the future !");
        }
        Event event = eventMapper.updateDTOToEntity(data);
        event.setOrganizer(eventOrganizer);
        event.setId(id);
        event.setImgUrl(eventToUpdate.getImgUrl());
        Event updatedEvent = eventDAO.save(event);
        return eventMapper.toResponseDTO(updatedEvent);
    }

    @Override
    public PaginationDTO<List<EventResponseDTO>> getAllEvents(int page , int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Organizer eventOrganizer = (Organizer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Event> events = eventDAO.findByOrganizer_Id( eventOrganizer.getId(), pageRequest);
        Page<EventResponseDTO> eventsResponse =  events.map(eventMapper::toResponseDTO);
        return new PaginationDTO<>(events.hasNext() , events.hasPrevious() , eventsResponse.getContent());
    }

    @Override
    public EventResponseDTO getEventById(Long id) {
        Event existingEvent = getEventEntityById(id);
        assertIsOrganizerEvent(existingEvent , "You Can't Get Data Of An Event That Ain't Yours !");
        return eventMapper.toResponseDTO(existingEvent);
    }

    @Override
    public EventResponseDTO deleteEvent(Long id) {
        Event existingEvent = getEventEntityById(id);
        assertIsOrganizerEvent(existingEvent , "You Can't Delete An Event That Ain't Yours !");
        eventDAO.deleteById(id);
        return eventMapper.toResponseDTO(existingEvent);
    }

    private boolean isValidDate(LocalDateTime date){
        return date.isAfter(LocalDateTime.now());
    }


    @Override
    public Event getEventEntityById(Long id){
        return eventDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Event Was Found With Given Id !"));
    }

    @Override
    public List<EventResponseDTO> getAllEventsNoPagination() {
        Organizer eventOrganizer = (Organizer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Event> res = eventDAO.findByOrganizer(eventOrganizer);
        return res.stream()
                .map(eventMapper::toResponseDTO)
                .toList();
    }

    private void assertIsOrganizerEvent(Event event , String message){
        Organizer eventOrganizer = (Organizer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!event.getOrganizer().getId().equals(eventOrganizer.getId())){
            throw new AccessDeniedException(message);
        }
    }
}

