package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DepartmentConverterTest {


    @Test
    public void toEntity() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Test1");
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        Department department = dtoToDepartmentConverter.convert(departmentDto);
        assertEquals(department.getDepartmentName(),departmentDto.getDepartmentName());
    }

    @Test
    public void toDto() {
        Department department = new Department();
        department.setDepartmentId(2);
        department.setDepartmentName("Test2");
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        DepartmentDto departmentDto = departmentToDtoConverter.convert(department);
        assertEquals(departmentDto.getDepartmentId(),department.getDepartmentId());
        assertEquals(departmentDto.getDepartmentName(),department.getDepartmentName());
    }

}
