package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;
    public DepartmentController (DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("departmentDto",departmentService.getAllDepartmentDtos());
        return "departments/listofdepartments";
    }
}


