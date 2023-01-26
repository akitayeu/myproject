package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.UserDto;
import com.samsolutions.kitayeu.myproject.entities.User;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class DtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(@NonNull UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
