package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class DtoToEmployeeConverter implements Converter<EmployeeDto, Employee> {
    @Override
    public Employee convert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        return employee;
    }
}
