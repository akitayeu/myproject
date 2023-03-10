package com.samsolutions.kitayeu.myproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyProjectGlobalException extends RuntimeException {

    private String code;
    private String message;

    public MyProjectGlobalException(String message) {
        super(message);
    }
}
