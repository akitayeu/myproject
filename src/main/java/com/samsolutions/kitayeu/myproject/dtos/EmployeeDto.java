package com.samsolutions.kitayeu.myproject.dtos;

import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDto {

    private int employeeId;
    private String firstname;
    private String lastname;
    private Department department;
    private Set<Role> role;

}

