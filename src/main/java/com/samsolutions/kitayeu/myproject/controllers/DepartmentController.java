package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public String readAll(Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        model.addAttribute("departmentDto", departmentService.getAllDepartments(page));
        return "departments/listofdepartments";
    }

    @GetMapping("/departments/{id}")
    public String showDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("departmentDto", departmentService.getById(id));
        return "departments/showdepartment";
    }

    @GetMapping("/departments/new")
    public String newDepartment(Model model) {
        model.addAttribute("departmentDto", new DepartmentDto());
        return "departments/new";
    }

    @PostMapping("/departments")
    public String createDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto) {
        departmentService.createDepartment(departmentDto);
        return "redirect:/api/departments";
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
        return "redirect:/api/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String editDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("departmentDto", departmentService.getById(id));
        return "departments/edit";
    }

    @PatchMapping("/departments/{id}")
    public String updateDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto, @PathVariable("id") int id) {
        departmentService.updateDepartment(departmentDto, id);
        return "redirect:/api/departments";
    }
}


