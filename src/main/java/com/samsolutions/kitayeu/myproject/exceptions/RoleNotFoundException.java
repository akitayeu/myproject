package com.samsolutions.kitayeu.myproject.exceptions;

public class RoleNotFoundException extends MyProjectGlobalException {
    public RoleNotFoundException(String message) {
        super(message, GlobalErrorCode.ERROR_ROLE_NOT_FOUND);
    }
}
