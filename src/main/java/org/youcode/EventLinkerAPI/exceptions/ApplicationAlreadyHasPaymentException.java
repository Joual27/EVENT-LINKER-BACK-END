package org.youcode.EventLinkerAPI.exceptions;

public class ApplicationAlreadyHasPaymentException extends RuntimeException {
    public ApplicationAlreadyHasPaymentException(String message) {
        super(message);
    }
}
