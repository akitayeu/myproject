package com.samsolutions.kitayeu.myproject.services;


import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments(int page);

    void deleteDepartment(int id);

    DepartmentDto updateDepartment(DepartmentDto departmentDto, int id);

    DepartmentDto getById(int id);
}
