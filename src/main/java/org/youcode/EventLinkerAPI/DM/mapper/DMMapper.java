package org.youcode.EventLinkerAPI.DM.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;

@Mapper(componentModel = "spring")
public interface DMMapper extends BaseResponseMapper<DM, DmResponseDTO> {
}
