package org.youcode.EventLinkerAPI.shared.utils.exceptionHandling.ws;


import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.ErrorDTO;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@ControllerAdvice
public class WebSocketGlobalExceptionHandler {
    @MessageExceptionHandler(AccessDeniedException.class)
    @SendToUser("/queue/errors")
    public ErrorDTO handleAccessDeniedException(AccessDeniedException e){
        return new ErrorDTO(HttpStatus.FORBIDDEN.value(), e.getMessage(), LocalDateTime.now());
    }

    @MessageExceptionHandler(EntityNotFoundException.class)
    @SendToUser("/queue/errors")
    public ErrorDTO handleEntityNotFoundException(EntityNotFoundException e){
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    }
}
