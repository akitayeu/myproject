package com.samsolutions.kitayeu.myproject.dtos;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String userName;
    private String userPassword;
    private String userMail;
}
