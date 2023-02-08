package com.samsolutions.kitayeu.myproject.repositories;

import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeAndUserTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    private Employee employee;
    private Employee employee1;
    private Department department;
    private Set<Role> roleSet = new HashSet<>();
    private Role role;
    private Role role1;

    private User user;
    private User user1;


    @BeforeEach
    public void createSomething() {
        department = new Department("Development");
        departmentRepository.saveAndFlush(department);
        user = new User("usertest", "mailtest@gmail.com", "testpassword");
        employee = new Employee();
        employee.setFirstname("Aliaksandr");
        employee.setLastname("Kitayeu");
        employee.setBirthdate(LocalDate.of(1991, 01, 14));
        employee.setGender('M');
        employee.setPassportId("B");
        employee.setPassportValidity(LocalDate.of(2025, 11, 14));
        employee.setDepartment(department);
        role = new Role("trainee000");
        roleRepository.saveAndFlush(role);
        role1 = new Role("Tester000");
        roleRepository.saveAndFlush(role1);
        roleSet.add(role);
        roleSet.add(role1);
        employee.setRole(roleSet);
        employee.setUser(user);
        employeeRepository.saveAndFlush(employee);
        userRepository.saveAndFlush(user);
        employee1 = new Employee();
        user1 = new User("usertest1", "mailtest1@gmail.com", "testpassword");
        employee1.setFirstname("Petr");
        employee1.setLastname("Petrov");
        employee1.setBirthdate(LocalDate.of(1990, 01, 01));
        employee1.setGender('M');
        employee1.setPassportId("C");
        employee1.setPassportValidity(LocalDate.of(2023, 10, 14));
        employee1.setDepartment(department);
        employee1.setRole(roleSet);
        employee1.setUser(user1);
        employeeRepository.saveAndFlush(employee1);
        userRepository.saveAndFlush(user1);
    }

    @Test
    public void readUpdateSomething() {
        Employee readEmployee = employeeRepository.findById(employee.getEmployeeId()).get();
        assertEquals("Kitayeu", readEmployee.getLastname());
        readEmployee.setLastname("Ivanov");
        employeeRepository.saveAndFlush(readEmployee);
    }

    @AfterEach
    public void deleteSomething() {
        employeeRepository.delete(employee);
        employeeRepository.delete(employee1);
        assertEquals(1, employeeRepository.findAll().size());
        assertEquals(2, departmentRepository.findAll().size());
        assertEquals(2, roleRepository.findAll().size());
        assertEquals(1, userRepository.findAll().size());
        departmentRepository.delete(department);
        roleRepository.delete(role);
        roleRepository.delete(role1);
        userRepository.delete(user);
        userRepository.delete(user1);
        assertEquals(1, employeeRepository.findAll().size());
        assertEquals(1, departmentRepository.findAll().size());
        assertEquals(0, roleRepository.findAll().size());
        assertEquals(1, userRepository.findAll().size());
    }
}
