package org.youcode.EventLinkerAPI.organizer.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.organizer.DTOs.EmbeddedOrganizerDTO;
import org.youcode.EventLinkerAPI.organizer.Organizer;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;

@Mapper(componentModel = "spring")
public interface OrganizerMapper extends BaseEmbeddedMapper<Organizer , EmbeddedOrganizerDTO> {
}
