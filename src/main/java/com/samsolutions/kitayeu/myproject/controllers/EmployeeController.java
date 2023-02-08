package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("employee",employeeService.getAllEmployees());
        return "employees/listofemployees";
    }
}


