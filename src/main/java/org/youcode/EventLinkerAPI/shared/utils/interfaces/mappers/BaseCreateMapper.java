package org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers;

public interface BaseCreateMapper<E , CreateDTO> {
    E toEntity(CreateDTO createDTO);
}
