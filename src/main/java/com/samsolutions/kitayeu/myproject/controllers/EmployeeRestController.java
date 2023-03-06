package com.samsolutions.kitayeu.myproject.controllers;

import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/page={page}")
    public ResponseEntity<List<EmployeeDto>> readAllEmployees(@PathVariable(name = "page") int page) {
        final List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees(page);
        return employeeDtoList != null && !employeeDtoList.isEmpty()
                ? new ResponseEntity<>(employeeDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") int id) {
        final EmployeeDto employeeDto = employeeService.getById(id);
        return employeeDto != null
                ? new ResponseEntity<>(employeeDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/department={departmentName}")
    public ResponseEntity<List<EmployeeDto>> readEmployeesInDepartment(@PathVariable(name = "departmentName")
                                                                       String departmentName) {
        final List<EmployeeDto> employeeDtoList = employeeService.getEmployeeByDepartment(departmentName);
        return employeeDtoList != null && !employeeDtoList.isEmpty()
                ? new ResponseEntity<>(employeeDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") int id) {
        final boolean deleted = employeeService.deleteEmployee(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable(name = "id") int id) {
        final Boolean updated = employeeService.updateEmployee(employeeDto, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        final EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return createdEmployee != null
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
