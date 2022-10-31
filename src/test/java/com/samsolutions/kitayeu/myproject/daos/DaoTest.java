package com.samsolutions.kitayeu.myproject.daos;

import com.samsolutions.kitayeu.myproject.entities.Department;

import javax.persistence.PersistenceContext;

public class DaoTest {

    @PersistenceContext
    private Department department;

    public Department getDepartment()
    {
        department.setDepartmentId(1);
        department.setDepartmentName("Developer");
        return department;
    }
}
