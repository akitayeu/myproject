package com.samsolutions.kitayeu.myproject.services;


import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartmentDto(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartmentDtos();

    void deleteDepartment(int id);

    DepartmentDto updateDepartmentDto(DepartmentDto departmentDto);

    DepartmentDto getById(int id);
}
