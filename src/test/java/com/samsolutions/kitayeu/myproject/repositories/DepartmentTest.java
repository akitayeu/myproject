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
    private int id1;

    @BeforeEach
    public void createAnything() {
        Department createdDepartment1 = new Department("Sales_Department");
        departmentRepository.saveAndFlush(createdDepartment1);
        id1 = createdDepartment1.getDepartmentId();
        Department createdDepartment2 = new Department("Economic");
        departmentRepository.saveAndFlush(createdDepartment2);
    }

    @Test
    public void readUpdateAnything() {
        Department readDepartment = departmentRepository.getOne(id1);
        assertEquals("Sales_Department", readDepartment.getDepartmentName());
        readDepartment.setDepartmentName("HR");
        departmentRepository.saveAndFlush(readDepartment);
    }

    @AfterEach
    public void deleteAnything() {
        Department deletedDepartment = departmentRepository.getReferenceById(id1);
        assertEquals("HR", deletedDepartment.getDepartmentName());
        departmentRepository.delete(deletedDepartment);
        assertEquals(1, departmentRepository.findAll().size());
    }
}
