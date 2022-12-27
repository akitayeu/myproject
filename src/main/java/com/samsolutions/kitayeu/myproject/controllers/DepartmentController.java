package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;
    @Value("${pageSize}")
    private int pageSize;
    public DepartmentController (DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @GetMapping()
    public String readAll(Model model,@RequestParam(defaultValue = "0", required = false) int page) {

        Pageable paging = PageRequest.of(page, pageSize);
        model.addAttribute("departmentDto",departmentService.getAllDepartmentDtos(page));
        return "departments/listofdepartments";
    }

    @GetMapping("/{id}")
    public String showDepartment(@PathVariable("id") int id, Model model){
        model.addAttribute("departmentDto",departmentService.getById(id));
        return "departments/showdepartment";
    }

    @GetMapping("/new")
    public String newDepartment(Model model){
        model.addAttribute("departmentDto",new DepartmentDto());
        return "departments/new";
    }

    @PostMapping()
    public String createDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto){
        departmentService.createDepartmentDto(departmentDto);
        return "redirect:/departments";
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable("id") int id){
    departmentService.deleteDepartment(id);
    return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String editDepartment(@PathVariable("id") int id, Model model){
        model.addAttribute("departmentDto",departmentService.getById(id));
        return "departments/edit";
    }

    @PatchMapping("/{id}")
    public String updateDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto, @PathVariable("id") int id){
        departmentService.updateDepartmentDto(departmentDto,id);
        return "redirect:/departments";
    }
}


