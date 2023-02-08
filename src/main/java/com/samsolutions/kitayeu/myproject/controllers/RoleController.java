package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("roleDto",roleService.getAllRole());
        return "roles/listofroles";
    }
}


