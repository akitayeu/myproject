package com.samsolutions.kitayeu.myproject.daos;

import com.samsolutions.kitayeu.myproject.entities.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentDAO departmentDAO;

    @BeforeEach
    public void createDepartment() {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName("HR111");
        departmentDAO.save(newDepartment);
    }

    @Test
    public void readDepartment() {
        Department readDepartment = departmentDAO.getAll().get(1);
        assertEquals("HR111", readDepartment.getDepartmentName());
        readDepartment.setDepartmentName("Sales department111");
        departmentDAO.update(readDepartment);
        assertEquals("Sales department111", readDepartment.getDepartmentName());
    }

    @AfterEach
    public void deleteDepartment() {
        Department deletedDepartment = departmentDAO.getAll().get(1);
        assertEquals("Sales department111", deletedDepartment.getDepartmentName());
        departmentDAO.delete(deletedDepartment);
        assertEquals(1, departmentDAO.getAll().size());
    }
}