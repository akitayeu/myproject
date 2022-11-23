package com.samsolutions.kitayeu.myproject.repositories;


import com.samsolutions.kitayeu.myproject.entities.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    private Department Department;

    @BeforeEach
    public void createAnything() {
        Department createdDepartment = new Department("Sales_Department");
        Department = departmentRepository.saveAndFlush(createdDepartment);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void readUpdateAnything() {
        Department readedDepartment = departmentRepository.getReferenceById(Department.getDepartmentId());
        assertEquals("Sales_Department", readedDepartment.getDepartmentName());
        readedDepartment.setDepartmentName("HR");
        departmentRepository.saveAndFlush(readedDepartment);
    }

    @AfterEach
    public void deleteAnything() {
        Department deletedDepartment = departmentRepository.getReferenceById(Department.getDepartmentId());
        departmentRepository.delete(deletedDepartment);
        assertEquals(0, departmentRepository.findAll().size());
    }
}
