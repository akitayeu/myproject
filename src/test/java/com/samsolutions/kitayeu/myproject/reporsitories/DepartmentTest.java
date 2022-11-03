package com.samsolutions.kitayeu.myproject.reporsitories;


import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

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
    assertEquals ("Sales_Department",readedDepartment.getDepartmentName());
    readedDepartment.setDepartmentName("HR");
    departmentRepository.saveAndFlush(readedDepartment);
    }

    @AfterEach
    public void deleteAnything (){
        Department readedDepartment = departmentRepository.getReferenceById(Department.getDepartmentId());
        departmentRepository.delete(readedDepartment);
        List<Department> departmentList = departmentRepository.findAll();
    }
}
