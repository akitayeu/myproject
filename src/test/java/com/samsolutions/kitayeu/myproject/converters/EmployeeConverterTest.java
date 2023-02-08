package com.samsolutions.kitayeu.myproject.converters;


import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class EmployeeConverterTest {

    @Test
    public void convertToDto() {
        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Department1");
        Set<Role> roleSet = new HashSet<>();
        Role role1 = new Role();
        Role role11 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("Test1");
        role11.setRoleId(11);
        role11.setRoleName("Test11");
        roleSet.add(role1);
        roleSet.add(role11);
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstname("Firstname1");
        employee.setLastname("Lastname1");
        employee.setDepartment(department);
        employee.setRole(roleSet);
        User user = new User("MyUser","mymail@gmail.com","");
        employee.setUser(user);
        EmployeeToDtoConverter employeeToDtoConverter = new EmployeeToDtoConverter();
        EmployeeDto employeeDto = employeeToDtoConverter.convert(employee);
        assert employeeDto != null;
        assertEquals(employeeDto.getEmployeeId(), employeeDto.getEmployeeId());
        assertEquals(employeeDto.getFirstname(), employeeDto.getFirstname());
        assertEquals(employeeDto.getLastname(), employeeDto.getLastname());
        assertEquals(employeeDto.getDepartmentDto().getDepartmentName(), employee.getDepartment().getDepartmentName());
        assertEquals(employeeDto.getRoleDtoSet().size(), employee.getRole().size());
    }
}
