package com.samsolutions.kitayeu.myproject.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userPasswordHash;
    private String userMail;
}
