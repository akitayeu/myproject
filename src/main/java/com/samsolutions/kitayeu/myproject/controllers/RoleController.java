package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("roleDto",roleService.getAllRoleDtos());
        return "roles/listofroles";
    }
}


