package com.samsolutions.kitayeu.myproject.exceptions;

public class EmployeeNotFoundException extends MyProjectGlobalException {
    public EmployeeNotFoundException(String message) {
        super(message, GlobalErrorCode.ERROR_EMPLOYEE_NOT_FOUND);
    }
}
