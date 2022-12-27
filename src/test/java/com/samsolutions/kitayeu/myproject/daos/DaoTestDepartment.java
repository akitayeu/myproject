package com.samsolutions.kitayeu.myproject.daos;

import com.samsolutions.kitayeu.myproject.entities.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
public class DaoTestDepartment {

    @Autowired
    private DepartmentDAO departmentDAO;

    private int id;

    @BeforeEach
    public void createDepartment() {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName("HR111");
        departmentDAO.save(newDepartment);
        id = newDepartment.getDepartmentId();
    }

    @Test
    public void readDepartment() {
        Department readedDepartment = departmentDAO.getAll().get(id);
        assertEquals("HR111", readedDepartment.getDepartmentName());
        readedDepartment.setDepartmentName("Sales department111");
        departmentDAO.update(readedDepartment);
        assertEquals("Sales department111", readedDepartment.getDepartmentName());
    }

    @AfterEach
    public void deleteDepartment() {
        Department deletedDepartment = departmentDAO.getAll().get(id);
        departmentDAO.delete(deletedDepartment);
        assertEquals(0, departmentDAO.getAll().size());
    }
}
