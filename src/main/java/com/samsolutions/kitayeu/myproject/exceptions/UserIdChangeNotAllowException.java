package com.samsolutions.kitayeu.myproject.exceptions;

public class UserIdChangeNotAllowException extends MyProjectGlobalException {
    public UserIdChangeNotAllowException(String message) {
        super(message, GlobalErrorCode.ERROR_USER_ID_CHANGE_NOT_ALLOW);
    }
}
