package com.samsolutions.kitayeu.myproject.exceptions;

public class UserNotFoundException extends MyProjectGlobalException {
    public UserNotFoundException(String message) {
        super(message, GlobalErrorCode.ERROR_USER_NOT_FOUND);
    }
}
