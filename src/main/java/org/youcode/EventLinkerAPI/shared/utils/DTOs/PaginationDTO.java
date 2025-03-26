package org.youcode.EventLinkerAPI.shared.utils.DTOs;

public record PaginationDTO<T>(boolean hasNext ,boolean hasPrevious , T data) {
}
