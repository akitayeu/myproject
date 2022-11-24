package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.Implementations.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;
    public DepartmentController (DepartmentServiceImpl departmentServiceImpl){
        this.departmentServiceImpl=departmentServiceImpl;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("department",departmentServiceImpl.getAllDepartments());
        return "departments/listofdepartments";
    }
}


