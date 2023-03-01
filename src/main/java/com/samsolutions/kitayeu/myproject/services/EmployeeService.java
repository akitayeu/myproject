package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees(int page);

    Boolean deleteEmployee(int id);

    Boolean updateEmployee(EmployeeDto employeeDto, int id);

    EmployeeDto getById(int id);

    List<EmployeeDto> getEmployeeByDepartment (String departmentName);
}
