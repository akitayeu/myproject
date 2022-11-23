package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.entities.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    void deleteDepartment(int id);

    Department updateDepartment(Department department);

    Department getById(int id);
}
