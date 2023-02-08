package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(int page);

    List<UserDto> getAllUsers();

    Boolean updateUser(UserDto UserDto, int id);

    UserDto getUserById(int id);
}
