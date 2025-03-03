package org.youcode.EventLinkerAPI.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.announcement.mapper.AnnouncementMapper;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.application.DTOs.CreateApplicationDTO;
import org.youcode.EventLinkerAPI.application.DTOs.EmbeddedApplicationDTO;
import org.youcode.EventLinkerAPI.application.DTOs.UpdateApplicationDTO;
import org.youcode.EventLinkerAPI.payment.mapper.PaymentMapper;
import org.youcode.EventLinkerAPI.review.mapper.ReviewMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseCreateMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseUpdateMapper;
import org.youcode.EventLinkerAPI.user.mapper.UserMapper;

@Mapper(componentModel = "spring" , uses = {AnnouncementMapper.class , UserMapper.class  , PaymentMapper.class})
public interface ApplicationMapper extends BaseCreateMapper<Application , CreateApplicationDTO> , BaseUpdateMapper<Application , UpdateApplicationDTO> , BaseResponseMapper<Application , ApplicationResponseDTO> , BaseEmbeddedMapper<Application , EmbeddedApplicationDTO> {
}
