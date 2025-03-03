package org.youcode.EventLinkerAPI.shared.utils.exceptionHandling.rest;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.youcode.EventLinkerAPI.exceptions.*;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.ErrorDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleValidationsException(MethodArgumentNotValidException e) {
        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                err -> validationErrors.put(err.getField(), err.getDefaultMessage())
        );
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", validationErrors, LocalDateTime.now());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBindException(BindException e) {
        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );
        e.getBindingResult().getGlobalErrors().forEach(error ->
                validationErrors.put(error.getObjectName(), error.getDefaultMessage())
        );
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                validationErrors,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleTokenExpiredException(TokenExpiredException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleBadCredentialsException(BadCredentialsException e) {
        return new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MaxPendingAnnouncementsReached.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleMaxPendingAnnouncementsReached(MaxPendingAnnouncementsReached e) {
        return new ErrorDTO(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnacceptedAnnouncementStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUnacceptedAnnouncementStatusException(UnacceptedAnnouncementStatusException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(PaymentProcessingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO handlePaymentProcessingException(PaymentProcessingException e) {
        return new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnpayableApplicationStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUnpayableApplicationStatusException(UnpayableApplicationStatusException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ApplicationAlreadyHasPaymentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO handleApplicationAlreadyHasPaymentException(ApplicationAlreadyHasPaymentException e) {
        return new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnsupportedActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUnsupportedActionException(UnsupportedActionException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO handleInsufficientBalanceException(InsufficientBalanceException e) {
        return new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(InvalidReviewConstraintsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleInsufficientBalanceException(InvalidReviewConstraintsException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }
}
