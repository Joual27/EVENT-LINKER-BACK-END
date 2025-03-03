package org.youcode.EventLinkerAPI.event.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.event.DTOs.CreateAndUpdateEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EmbeddedEventDTO;
import org.youcode.EventLinkerAPI.event.DTOs.EventResponseDTO;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseCreateMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;

@Mapper(componentModel = "spring")
public interface EventMapper extends BaseResponseMapper<Event , EventResponseDTO> , BaseCreateMapper<Event , CreateAndUpdateEventDTO> , BaseEmbeddedMapper<Event , EmbeddedEventDTO> {
}
