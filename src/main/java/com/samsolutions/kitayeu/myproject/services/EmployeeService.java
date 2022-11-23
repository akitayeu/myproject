package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    void deleteEmployee(int id);

    Employee updateEmployee(Employee employee);

    Employee getById(int id);
}
