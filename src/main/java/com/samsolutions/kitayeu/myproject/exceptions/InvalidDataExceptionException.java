package com.samsolutions.kitayeu.myproject.exceptions;

public class InvalidDataExceptionException extends MyProjectGlobalException {
    public InvalidDataExceptionException(String message) {
        super(message, GlobalErrorCode.ERROR_INVALID_DATA);
    }
}
