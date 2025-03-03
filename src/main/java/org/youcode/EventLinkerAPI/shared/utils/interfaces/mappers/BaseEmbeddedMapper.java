package org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers;

public interface BaseEmbeddedMapper <E , EmbeddedDTO> {
    EmbeddedDTO toEmbeddedDTO(E entity);
}
