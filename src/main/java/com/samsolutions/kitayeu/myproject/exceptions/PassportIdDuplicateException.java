package com.samsolutions.kitayeu.myproject.exceptions;

public class PassportIdDuplicateException extends MyProjectGlobalException {
    public PassportIdDuplicateException(String message) {
        super(message, GlobalErrorCode.ERROR_PASSPORT_ID_DUPLICATE);
    }
}
