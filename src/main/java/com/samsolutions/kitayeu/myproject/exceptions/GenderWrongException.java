package com.samsolutions.kitayeu.myproject.exceptions;

public class GenderWrongException extends MyProjectGlobalException {
    public GenderWrongException(String message) {
        super(message, GlobalErrorCode.ERROR_GENDER_WRONG);
    }
}
