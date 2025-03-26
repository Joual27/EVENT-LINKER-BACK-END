package org.youcode.EventLinkerAPI.event.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.event.DTOs.CreateEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EmbeddedEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EventResponseDTO;
import org.youcode.EventLinkerAPI.event.DTOs.UpdateEventDTO;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseCreateMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseUpdateMapper;

@Mapper(componentModel = "spring")
public interface EventMapper extends BaseResponseMapper<Event , EventResponseDTO> , BaseCreateMapper<Event , CreateEventDTO> , BaseEmbeddedMapper<Event , EmbeddedEventDTO> , BaseUpdateMapper<Event , UpdateEventDTO> {
}
