package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String readAll(Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        model.addAttribute("employee", employeeService.getAllEmployees(page));
        return "employees/listofemployees";
    }
}


