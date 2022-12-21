package com.samsolutions.kitayeu.myproject.repositories;

import com.samsolutions.kitayeu.myproject.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
