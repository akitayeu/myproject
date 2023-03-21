package com.samsolutions.kitayeu.myproject.controllers.REST;

import com.samsolutions.kitayeu.myproject.dtos.UserDto;
import com.samsolutions.kitayeu.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/page={page}")
    final ResponseEntity<List<UserDto>> readAllUsers(@PathVariable(name = "page") int page) {
        final List<UserDto> userDtoList = userService.getAllUsers(page);
        return userDtoList != null && !userDtoList.isEmpty()
                ? new ResponseEntity<>(userDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") int id) {
        final UserDto userDto = userService.getUserById(id);
        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable(name = "id") int id) {
        final Boolean updated = userService.updateUser(userDto, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
