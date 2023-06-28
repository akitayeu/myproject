package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.UserDto;
import com.samsolutions.kitayeu.myproject.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class DtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user, "userPasswordHash");
        if (StringUtils.isNotBlank(userDto.getUserPasswordHash())) {
                user.setUserPasswordHash(userDto.getUserPasswordHash());
                return user;
        } else {
            user.setUserPasswordHash("");
            return user;
        }
    }
}
