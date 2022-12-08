package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl){
        this.employeeServiceImpl=employeeServiceImpl;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("employee",employeeServiceImpl.getAllEmployees());
        return "employees/listofemployees";
    }
}


