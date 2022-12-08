package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import org.jetbrains.annotations.NotNull;

public class EmployeeConverter {

    public EmployeeDto toDto(@NotNull Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstname(employee.getFirstname());
        employeeDto.setLastname(employee.getLastname());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setRole(employee.getRole());
        return employeeDto;
    }

    public Employee toEntity(@NotNull EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setRole(employeeDto.getRole());
        return employee;
    }
}
