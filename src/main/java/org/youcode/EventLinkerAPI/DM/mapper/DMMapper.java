package org.youcode.EventLinkerAPI.DM.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.DM.DTOs.DmResponseDTO;
import org.youcode.EventLinkerAPI.message.mapper.MessageMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;
import org.youcode.EventLinkerAPI.user.mapper.UserMapper;

@Mapper(componentModel = "spring" , uses = {UserMapper.class , MessageMapper.class})
public interface DMMapper extends BaseResponseMapper<DM, DmResponseDTO> {
}
