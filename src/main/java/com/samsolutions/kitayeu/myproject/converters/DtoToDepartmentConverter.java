package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class DtoToDepartmentConverter implements Converter<DepartmentDto, Department> {

    @Override
    public Department convert(DepartmentDto departmentDto) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        return department;
    }
}
