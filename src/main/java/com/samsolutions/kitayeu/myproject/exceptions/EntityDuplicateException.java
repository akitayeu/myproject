package com.samsolutions.kitayeu.myproject.exceptions;

public class EntityDuplicateException extends MyProjectGlobalException {
    public EntityDuplicateException(String message) {
        super(message, GlobalErrorCode.ERROR_ENTITY_DUPLICATE);
    }
}
