package com.samsolutions.kitayeu.myproject.exceptions;

public class UserNameDuplicateException extends MyProjectGlobalException {
    public UserNameDuplicateException(String message) {
        super(message, GlobalErrorCode.ERROR_USER_NAME_DUPLICATE);
    }
}
