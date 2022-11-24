package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.Implementations.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;
    public RoleController(RoleServiceImpl roleServiceImpl){
        this.roleServiceImpl=roleServiceImpl;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("role",roleServiceImpl.getAllRoles());
        return "roles/listofroles";
    }
}


