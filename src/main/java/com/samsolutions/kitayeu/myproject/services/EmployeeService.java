package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployeeDto(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeeDtos();

    void deleteEmployee(int id);

    EmployeeDto updateEmployeeDto(EmployeeDto employeeDto);

    EmployeeDto getById(int id);
}
