package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    void deleteEmployee(int id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    EmployeeDto getById(int id);
}
