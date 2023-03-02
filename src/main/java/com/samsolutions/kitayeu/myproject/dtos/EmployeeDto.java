package com.samsolutions.kitayeu.myproject.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeDto {

    private Integer employeeId;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private char gender;
    private String passportId;
    private LocalDate passportValidity;
    private DepartmentDto departmentDto;
    private Set<RoleDto> roleDtoSet;
    private UserDto userDto;

}

