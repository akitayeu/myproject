package com.samsolutions.kitayeu.myproject.repositories;

import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
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
    private RoleRepository roleRepository;
    private Employee employee;
    private Employee employee1;
    private Department department;
    private Set<Role> roleSet = new HashSet<>();
    private Role role;
    private Role role1;
    private int id;

    @BeforeEach
    public void createSomething() {
        department = new Department("Developer");
        departmentRepository.saveAndFlush(department);
        employee = new Employee();
        employee.setFirstname("Aliaksandr");
        employee.setLastname("Kitayeu");
        employee.setBirthdate(LocalDate.of(1991, 01, 14));
        employee.setGender('M');
        employee.setPassportId("B");
        employee.setPassportValidity(LocalDate.of(2025, 11, 14));
        employee.setDepartment(department);
        role = new Role("trainee");
        roleRepository.saveAndFlush(role);
        role1 = new Role("Tester");
        roleRepository.saveAndFlush(role1);
        roleSet.add(role);
        roleSet.add(role1);
        employee.setRole(roleSet);
        employeeRepository.saveAndFlush(employee);
        id = employee.getEmployeeId();
        employee1 = new Employee();
        employee1.setFirstname("Petr");
        employee1.setLastname("Petrov");
        employee1.setBirthdate(LocalDate.of(1990, 01, 01));
        employee1.setGender('M');
        employee1.setPassportId("C");
        employee1.setPassportValidity(LocalDate.of(2023, 10, 14));
        employee1.setDepartment(department);
        employee1.setRole(roleSet);
        employeeRepository.saveAndFlush(employee1);
    }

    @Test
    public void readUpdateSomething() {
        Employee readEmployee = employeeRepository.findById(id).get();
        assertEquals("Kitayeu", readEmployee.getLastname());
        readEmployee.setLastname("Ivanov");
        employeeRepository.save(readEmployee);
    }

    @AfterEach
    public void deleteSomething() {
        Employee deletedEmployee = employeeRepository.findById(id).get();
        assertEquals("Ivanov", deletedEmployee.getLastname());
        employeeRepository.delete(deletedEmployee);
        assertEquals(1, employeeRepository.findAll().size());
        assertEquals(1, departmentRepository.findAll().size());
        assertEquals(2, roleRepository.findAll().size());
    }
}
