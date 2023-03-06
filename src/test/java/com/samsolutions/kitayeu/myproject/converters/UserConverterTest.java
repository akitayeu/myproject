package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.UserDto;
import com.samsolutions.kitayeu.myproject.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserConverterTest {

    @Test
    public void toEntity() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("Test");
        userDto.setUserPasswordHash("123");
        userDto.setUserMail("a@123.gmail");
        DtoToUserConverter dtoToUserConverter = new DtoToUserConverter();
        User user = dtoToUserConverter.convert(userDto);
        assert user != null;
        assertEquals(user.getUserId(),userDto.getUserId());
        assertEquals(user.getUserName(),userDto.getUserName());
        assertEquals(user.getUserPasswordHash(),userDto.getUserPasswordHash());
        assertEquals(user.getUserMail(),userDto.getUserMail());
    }

    @Test
    public void toDto() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Test");
        user.setUserPasswordHash("123");
        user.setUserMail("a@123.gmail");
        UserToUserDtoConverter userToDtoConverter = new UserToUserDtoConverter();
        UserDto userDto = userToDtoConverter.convert(user);
        assert userDto != null;
        assertEquals(userDto.getUserId(),user.getUserId());
        assertEquals(userDto.getUserName(),user.getUserName());
        assertEquals(userDto.getUserPasswordHash(),"");
        assertEquals(userDto.getUserMail(),user.getUserMail());
    }

}