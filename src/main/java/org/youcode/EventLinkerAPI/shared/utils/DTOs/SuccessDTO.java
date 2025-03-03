package org.youcode.EventLinkerAPI.shared.utils.DTOs;

public record SuccessDTO<T>(String status , String message , T data) {
}
