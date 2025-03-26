package org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers;

public interface BaseResponseMapper<E , ResponseDTO> {
    ResponseDTO toResponseDTO(E entity);
}
