package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.sun.istack.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

public class EmployeeToDtoConverter implements Converter<Employee, EmployeeDto> {

    @Override
    public EmployeeDto convert(@NotNull Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        employeeDto.setDepartmentDto(departmentToDtoConverter.convert(employee.getDepartment()));
        Set<RoleDto> roleDtoSet = new HashSet<>();
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        for (Role role : employee.getRole()) {
            roleDtoSet.add(roleToDtoConverter.convert(role));
        }
        employeeDto.setRoleDtoSet(roleDtoSet);
        return employeeDto;
    }

}
