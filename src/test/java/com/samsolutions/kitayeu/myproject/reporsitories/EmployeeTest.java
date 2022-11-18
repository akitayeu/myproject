package com.samsolutions.kitayeu.myproject.reporsitories;

import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentRepository roleRepository;
    private Employee employee;
    private Department department;
    private Set<Role> roleSet = new HashSet<>();
    private Role role;

    @BeforeEach
    public void createAnything() {
        department = new Department("economic department");
        role = new Role("trainee");
        roleSet.add(role);
        employee = new Employee();
        employee.setFirstname("Aliaksandr");
        employee.setLastname("Kitayeu");
        employee.setBirthdate(LocalDate.of(1991, 01, 14));
        employee.setGender('M');
        employee.setPassportId("B");
        employee.setPassportValidity(LocalDate.of(2025, 11, 14));
        employee.setDepartment(department);
        employee.setRole(roleSet);
        employeeRepository.saveAndFlush(employee);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void readUpdateAnything() {
        Employee readedEmployee = employeeRepository.getReferenceById(employee.getEmployeeId());
        assertEquals("Kitayeu", readedEmployee.getLastname());
        readedEmployee.setFirstname("Mark");
        readedEmployee.setLastname("Ivanov");
        readedEmployee.setBirthdate(LocalDate.of(2000, 01, 01));
        readedEmployee.setGender('M');
        readedEmployee.setPassportId("C");
        readedEmployee.setPassportValidity(LocalDate.of(2027, 01, 01));
        readedEmployee.setDepartment(department);
        readedEmployee.setRole(roleSet);
        employeeRepository.saveAndFlush(readedEmployee);
    }

    @AfterEach
    public void deleteAnything() {
        Employee deletedEmployee = employeeRepository.getReferenceById(employee.getEmployeeId());
        employeeRepository.delete(deletedEmployee);
        assertEquals(0, employeeRepository.findAll().size());
        assertEquals(1, departmentRepository.findAll().size());
        assertEquals(1, roleRepository.findAll().size());
    }
}
