package org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers;

public interface BaseUpdateMapper<E, UpdateDTO>{
    E updateDTOToEntity(UpdateDTO updateDTO);
}
