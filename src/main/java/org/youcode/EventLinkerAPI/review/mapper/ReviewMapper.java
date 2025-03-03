package org.youcode.EventLinkerAPI.review.mapper;


import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.application.mapper.ApplicationMapper;
import org.youcode.EventLinkerAPI.review.DTOs.EmbeddedReviewDTO;
import org.youcode.EventLinkerAPI.review.DTOs.ReviewResponseDTO;
import org.youcode.EventLinkerAPI.review.DTOs.SubmitReviewDTO;
import org.youcode.EventLinkerAPI.review.Review;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseCreateMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseResponseMapper;
import org.youcode.EventLinkerAPI.user.mapper.UserMapper;

@Mapper(componentModel = "Spring" , uses = {UserMapper.class , ApplicationMapper.class})
public interface ReviewMapper extends BaseEmbeddedMapper<Review , EmbeddedReviewDTO> , BaseCreateMapper<Review , SubmitReviewDTO> , BaseResponseMapper<Review , ReviewResponseDTO> {
}
