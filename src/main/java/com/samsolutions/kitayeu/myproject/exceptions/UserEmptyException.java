package com.samsolutions.kitayeu.myproject.exceptions;

public class UserEmptyException extends MyProjectGlobalException {
    public UserEmptyException(String message) {
        super(message, GlobalErrorCode.ERROR_USER_EMPTY);
    }
}
