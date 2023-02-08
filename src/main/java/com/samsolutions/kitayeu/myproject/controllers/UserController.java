package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.dtos.UserDto;
import com.samsolutions.kitayeu.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    @Value("5")
    private int pageSize;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/page={pageSize}")
    final ResponseEntity<List<UserDto>> readAllUsers(@PathVariable(name = "pageSize") int page) {
        Pageable paging = PageRequest.of(page, pageSize);
        final List<UserDto> userDtoList = userService.getAllUsers(page);
        return userDtoList != null && !userDtoList.isEmpty()
                ? new ResponseEntity<>(userDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> readAllUsers() {
        final List<UserDto> userDtoList = userService.getAllUsers();
        return userDtoList != null && !userDtoList.isEmpty()
                ? new ResponseEntity<>(userDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") int id) {
        final UserDto userDto = userService.getUserById(id);
        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser( @RequestBody UserDto userDto,@PathVariable(name = "id") int id) {
        final Boolean updated = userService.updateUser(userDto,id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
