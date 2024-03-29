package com.samsolutions.kitayeu.myproject.controllers.MVC;

import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String readAll(Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        model.addAttribute("departmentDto", departmentService.getAllDepartments(page));
        return "departments/listofdepartments";
    }

    @GetMapping("/{id}")
    public String showDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("departmentDto", departmentService.getById(id));
        return "departments/showdepartment";
    }

    @GetMapping("/new")
    public String newDepartment(Model model) {
        model.addAttribute("departmentDto", new DepartmentDto());
        return "departments/new";
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PostMapping
    public String createDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto) {
        departmentService.createDepartment(departmentDto);
        return "redirect:/departments";
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String editDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("departmentDto", departmentService.getById(id));
        return "departments/edit";
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PatchMapping("/{id}")
    public String updateDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto, @PathVariable("id") int id) {
        departmentService.updateDepartment(departmentDto, id);
        return "redirect:/departments";
    }
}


