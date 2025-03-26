package org.youcode.EventLinkerAPI.announcement.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.EventLinkerAPI.announcement.Announcement;
import org.youcode.EventLinkerAPI.announcement.DTOs.AnnouncementResponseDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.CreateAnnouncementDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.EmbeddedAnnouncementDTO;
import org.youcode.EventLinkerAPI.announcement.DTOs.UpdateAnnouncementDTO;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseCreateMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseUpdateMapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper extends BaseEmbeddedMapper<Announcement , EmbeddedAnnouncementDTO> , BaseCreateMapper<Announcement , CreateAnnouncementDTO> , BaseUpdateMapper<Announcement , UpdateAnnouncementDTO> , BaseResponseMapper<Announcement , AnnouncementResponseDTO> {

    @Override
    @Mapping(target = "event.id" , source = "eventId")
    Announcement toEntity(CreateAnnouncementDTO dto);
}
