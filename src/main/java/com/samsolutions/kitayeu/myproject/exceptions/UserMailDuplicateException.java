package com.samsolutions.kitayeu.myproject.exceptions;

public class UserMailDuplicateException extends MyProjectGlobalException {
    public UserMailDuplicateException(String message) {
        super(message, GlobalErrorCode.ERROR_USER_MAIL_DUPLICATE);
    }
}
