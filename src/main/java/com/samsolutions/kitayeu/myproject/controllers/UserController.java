package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String readAll(Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        model.addAttribute("userDto", userService.getAllUsers(page));
        return "users/listofusers";
    }
}


