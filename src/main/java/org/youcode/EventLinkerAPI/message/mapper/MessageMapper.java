package org.youcode.EventLinkerAPI.message.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;
import org.youcode.EventLinkerAPI.message.DTOs.SendMessageDTO;
import org.youcode.EventLinkerAPI.message.Message;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseCreateMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;
import org.youcode.EventLinkerAPI.user.mapper.UserMapper;

@Mapper(componentModel = "spring" , uses = UserMapper.class)
public interface MessageMapper extends BaseCreateMapper<Message , SendMessageDTO> , BaseResponseMapper<Message , MessageResponseDTO> {
    @Override
    @Mapping(target = "dmId", source = "dm.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    MessageResponseDTO toResponseDTO(Message message);
}
