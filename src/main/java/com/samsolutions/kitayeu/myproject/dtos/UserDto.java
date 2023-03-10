package com.samsolutions.kitayeu.myproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {
    private Integer userId;

    private String userName;

    private String userPassword;

    @JsonIgnore
    private String userPasswordHash;

    private String userMail;
}
