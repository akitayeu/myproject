package com.samsolutions.kitayeu.myproject.exceptions;

public class IdMismatchException extends MyProjectGlobalException {
    public IdMismatchException(String message) {
        super(message, GlobalErrorCode.ERROR_ID_MISMATCH);
    }
}
