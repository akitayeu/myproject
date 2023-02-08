package com.samsolutions.kitayeu.myproject.exceptions;

public class DeleteOrChangeEntityNotAllowException extends MyProjectGlobalException {
    public DeleteOrChangeEntityNotAllowException(String message) {
        super(message, GlobalErrorCode.ERROR_DELETE_OR_CHANGE_ENTITY_NOT_ALLOW);
    }
}
