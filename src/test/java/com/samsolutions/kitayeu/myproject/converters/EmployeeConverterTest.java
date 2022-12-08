package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class EmployeeConverterTest {


    @Test
    public void convertToEntity() {
        Department department = new Department("Department1");
        Set<Role> roleSet = new HashSet<>();
        Role role1 = new Role("Role1");
        Role role11 = new Role("Role11");
        roleSet.add(role1);
        roleSet.add(role11);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(1);
        employeeDto.setFirstname("Firstname1");
        employeeDto.setLastname("Lastname1");
        employeeDto.setDepartment(department);
        employeeDto.setRole(roleSet);
        EmployeeConverter employeeConvertor = new EmployeeConverter();
        Employee employee = employeeConvertor.toEntity(employeeDto);
        assertEquals(employee.getEmployeeId(), employeeDto.getEmployeeId());
        assertEquals(employee.getFirstname(), employeeDto.getFirstname());
        assertEquals(employee.getLastname(), employeeDto.getLastname());
        assertEquals(employee.getDepartment(), employeeDto.getDepartment());
        assertEquals(employee.getRole(), employeeDto.getRole());
    }

    @Test
    public void convertToDto() {
        Department department2 = new Department("Department2");
        Set<Role> roleSet2 = new HashSet<>();
        Role role2 = new Role("Role2");
        Role role22 = new Role("Role22");
        roleSet2.add(role2);
        roleSet2.add(role22);
        Employee employee = new Employee();
        employee.setEmployeeId(2);
        employee.setFirstname("Firstname2");
        employee.setLastname("Lastname2");
        employee.setDepartment(department2);
        employee.setRole(roleSet2);
        EmployeeConverter employeeConvertor = new EmployeeConverter();
        EmployeeDto employeeDto = employeeConvertor.toDto(employee);
        assertEquals(employeeDto.getEmployeeId(), employee.getEmployeeId());
        assertEquals(employeeDto.getFirstname(), employee.getFirstname());
        assertEquals(employeeDto.getLastname(), employee.getLastname());
        assertEquals(employeeDto.getDepartment(), employee.getDepartment());
        assertEquals(employeeDto.getRole(), employee.getRole());
    }
}
