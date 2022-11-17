package com.samsolutions.kitayeu.myproject.reporsitories;

import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    private Employee employee;
    private Department department;

    @BeforeEach
    public void createAnything() {
        department = new Department("economic department");
        employee = new Employee();
        employee.setFirstname("Aliaksandr");
        employee.setLastname("Kitayeu");
        employee.setBirthdate(LocalDate.of(1991, 01, 14));
        employee.setGender('M');
        employee.setPassportId("B");
        employee.setPassportValidity(LocalDate.of(2025, 11, 14));
        employee.setDepartment(department);
        employeeRepository.saveAndFlush(employee);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void readUpdateAnything() {
        Employee readedEmployee = employeeRepository.getReferenceById(employee.getEmployeeId());
        assertEquals("Kitayeu", readedEmployee.getLastname());
    }

    @AfterEach
    public void deleteAnything() {
        Employee readedEmployee = employeeRepository.getReferenceById(employee.getEmployeeId());
        employeeRepository.delete(readedEmployee);
        assertEquals(0, employeeRepository.findAll().size());
        assertEquals(0,departmentRepository.findAll().size());
    }

}
