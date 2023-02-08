package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.EmployeeToDtoConverter;
import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import com.samsolutions.kitayeu.myproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        EmployeeToDtoConverter employeeToDtoConverter = new EmployeeToDtoConverter();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee : employeeList) {
            employeeDtoList.add(employeeToDtoConverter.convert(employee));
        }
        return employeeDtoList;
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {

        return employeeDto;
    }

    @Override
    public EmployeeDto getById(int id) {
        EmployeeToDtoConverter employeeToDtoConverter = new EmployeeToDtoConverter();
        return employeeToDtoConverter.convert(employeeRepository.getReferenceById(id));
    }
}
