package com.samsolutions.kitayeu.myproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MyProjectGlobalException.class)
    protected ResponseEntity handleGlobalException(MyProjectGlobalException myProjectGlobalException, Locale locale) {
        if (myProjectGlobalException.getCode().equals("404")) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder()
                            .code(myProjectGlobalException.getCode())
                            .message(myProjectGlobalException.getMessage())
                            .build());

        } else if (myProjectGlobalException.getCode().equals("409")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ErrorResponse.builder()
                            .code(myProjectGlobalException.getCode())
                            .message(myProjectGlobalException.getMessage())
                            .build());

        } else if (myProjectGlobalException.getCode().equals("403")) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(ErrorResponse.builder()
                            .code(myProjectGlobalException.getCode())
                            .message(myProjectGlobalException.getMessage())
                            .build());

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorResponse.builder()
                            .code(myProjectGlobalException.getCode())
                            .message(myProjectGlobalException.getMessage())
                            .build());
        }
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body("Exception occur inside API " + e);
    }
}
