package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.Implementations.DepartmentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImplementation departmentServiceImplementation;
    public DepartmentController (DepartmentServiceImplementation departmentServiceImplementation){
        this.departmentServiceImplementation=departmentServiceImplementation;
    }

    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("departments",departmentServiceImplementation.getAllDepartments());
        return "departments/index";
    }
}
