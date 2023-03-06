package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class DepartmentToDtoConverter implements Converter<Department, DepartmentDto> {

    @Override
    public DepartmentDto convert(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        BeanUtils.copyProperties(department, departmentDto);
        return departmentDto;
    }
}
