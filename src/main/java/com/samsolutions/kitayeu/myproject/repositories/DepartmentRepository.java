package com.samsolutions.kitayeu.myproject.repositories;

import com.samsolutions.kitayeu.myproject.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Boolean existsByDepartmentName(String departmentName);
}
