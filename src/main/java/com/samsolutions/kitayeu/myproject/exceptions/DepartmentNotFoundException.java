package com.samsolutions.kitayeu.myproject.exceptions;

public class DepartmentNotFoundException extends MyProjectGlobalException {
    public DepartmentNotFoundException(String message) {
        super(message, GlobalErrorCode.ERROR_DEPARTMENT_NOT_FOUND);
    }
}
