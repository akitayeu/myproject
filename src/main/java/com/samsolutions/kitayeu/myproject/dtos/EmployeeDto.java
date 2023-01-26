package com.samsolutions.kitayeu.myproject.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDto {

    private Integer employeeId;
    private String firstname;
    private String lastname;
    private DepartmentDto departmentDto;
    private Set<RoleDto> roleDtoSet;

}

