package com.samsolutions.kitayeu.myproject.repositories;


import com.samsolutions.kitayeu.myproject.entities.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    private int id1;
    private int id2;

    @BeforeEach
    public void createSomething() {
        Department createdDepartment1 = new Department("Sales_Department777");
        departmentRepository.saveAndFlush(createdDepartment1);
        id1 = createdDepartment1.getDepartmentId();
        Department createdDepartment2 = new Department("Economic777");
        departmentRepository.saveAndFlush(createdDepartment2);
        id2 = createdDepartment2.getDepartmentId();
    }

    @Test
    public void readUpdateSomething() {
        Department readDepartment = departmentRepository.findById(id1).get();
        assertEquals("Sales_Department777", readDepartment.getDepartmentName());
        readDepartment.setDepartmentName("HR777");
        departmentRepository.saveAndFlush(readDepartment);
    }

    @AfterEach
    public void deleteSomething() {
        Department deletedDepartment1 = departmentRepository.findById(id1).get();
        Department deletedDepartment2 = departmentRepository.findById(id2).get();
        departmentRepository.delete(deletedDepartment1);
        departmentRepository.delete(deletedDepartment2);
        assertTrue(departmentRepository.findById(id1).isEmpty());
        assertTrue(departmentRepository.findById(id2).isEmpty());
    }
}
