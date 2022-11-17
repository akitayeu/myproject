package com.samsolutions.kitayeu.myproject.daos;

import com.samsolutions.kitayeu.myproject.entities.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


@SpringBootTest
public class DaoTestDepartment {

    @Autowired
    private DepartmentDAO departmentDAO;

    private int id;

    @BeforeEach
    public void createDepartment() {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName("HR");
        departmentDAO.save(newDepartment);
        this.id=newDepartment.getDepartmentId();
    }

    @Test
    public void readDepartment() {
        Department readedDepartment = departmentDAO.getAll().get(this.id);
        assertEquals("HR", readedDepartment.getDepartmentName());
        readedDepartment.setDepartmentName("Sales department");
        departmentDAO.update(readedDepartment);
        assertEquals("Sales department", readedDepartment.getDepartmentName());
    }

    @AfterEach
    public void deleteDepartment() {
        Department deletedDepartment = departmentDAO.getAll().get(this.id);
        departmentDAO.delete(deletedDepartment);
        assertEquals(0, departmentDAO.getAll().size());
    }
}
