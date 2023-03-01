package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DtoToEmployeeConverter;
import com.samsolutions.kitayeu.myproject.converters.DtoToRoleConverter;
import com.samsolutions.kitayeu.myproject.converters.EmployeeToDtoConverter;
import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.exceptions.*;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import com.samsolutions.kitayeu.myproject.repositories.RoleRepository;
import com.samsolutions.kitayeu.myproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${pageSize}")
    private int pageSize;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<EmployeeDto> getAllEmployees(int page) {
        Pageable paging = PageRequest.of(page, pageSize);
        EmployeeToDtoConverter employeeToDtoConverter = new EmployeeToDtoConverter();
        return employeeRepository.findAll(paging).stream()
                .map(employeeToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(int id) {
        EmployeeToDtoConverter employeeToDtoConverter = new EmployeeToDtoConverter();
        if (employeeRepository.findById(id).isPresent()) {
            return employeeToDtoConverter.convert(employeeRepository.findById(id).get());
        } else {
            return null;
        }
    }

    @Override
    public List<EmployeeDto> getEmployeeByDepartment(String departmentName) {
        EmployeeToDtoConverter employeeToDtoConverter = new EmployeeToDtoConverter();
        if (!employeeRepository.findEmployeesByDepartment(departmentRepository.findDepartmentByDepartmentName(departmentName)).isEmpty()) {
            return employeeRepository.findEmployeesByDepartment(departmentRepository.findDepartmentByDepartmentName(departmentName)).stream()
                    .map(employeeToDtoConverter::convert)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean deleteEmployee(int id) {
        if (id == 0) { // userId can`t be changed
            throw new UserIdChangeNotAllowException("1005");
        } else {
            if (employeeRepository.existsEmployeeByEmployeeId(id)) {
                employeeRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    @Transactional
    public Boolean updateEmployee(EmployeeDto employeeDto, int id) {
        DtoToEmployeeConverter dtoToEmployeeConverter = new DtoToEmployeeConverter();
        Employee updatedEmployee = dtoToEmployeeConverter.convert(employeeDto);

        if (id == 0) {  // EmployeeId=0 is reserved and can`t be changed
            throw new DeleteOrChangeEntityNotAllowException("1001");
        }
        if (updatedEmployee.getEmployeeId() != null) {
            if (id != updatedEmployee.getEmployeeId()) { // EmployeeId can`t be changed
                throw new UserIdChangeNotAllowException("1005");
            }
        }

        Employee readEmployee = employeeRepository.findById(id).orElse(null);
        if (readEmployee == null) {
            throw new EmployeeNotFoundException("1009");
        }

        if (employeeDto.getDepartmentDto() != null) {
            if (!departmentRepository.existsByDepartmentName(employeeDto.getDepartmentDto().getDepartmentName())) {
                throw new DepartmentNotFoundException("1002");
            } else {
                updatedEmployee.setDepartment(departmentRepository.findDepartmentByDepartmentName(employeeDto.getDepartmentDto().getDepartmentName()));
            }
        } else {
            updatedEmployee.setDepartment(readEmployee.getDepartment());
        }

        if (employeeDto.getRoleDtoSet() != null) {
            DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
            Set<Role> receiveRoles = new HashSet<>();
            for (RoleDto dtoRole : employeeDto.getRoleDtoSet()) {
                if (!roleRepository.existsRoleByRoleName(dtoToRoleConverter.convert(dtoRole).getRoleName())) {
                    throw new RoleNotFoundException("1010");
                } else {
                    receiveRoles.add(dtoToRoleConverter.convert(dtoRole));
                }
            }
            updatedEmployee.setRole(receiveRoles);
        } else {
            updatedEmployee.setRole(readEmployee.getRole());
        }

        if (updatedEmployee.getFirstname() == null) {
            updatedEmployee.setFirstname(readEmployee.getFirstname());
        }
        if (updatedEmployee.getLastname() == null) {
            updatedEmployee.setLastname(readEmployee.getLastname());

        }
        if (updatedEmployee.getBirthdate() == null) {
            updatedEmployee.setBirthdate(readEmployee.getBirthdate());

        }

        if (String.valueOf(updatedEmployee.getGender()).equalsIgnoreCase("m")) {
            updatedEmployee.setGender('m');
        } else if (String.valueOf(updatedEmployee.getGender()).equalsIgnoreCase("w")) {
            updatedEmployee.setGender('w');
        } else {
            updatedEmployee.setGender(readEmployee.getGender());
        }

        if (updatedEmployee.getPassportId() != null && !updatedEmployee.getPassportId().equals(readEmployee.getPassportId())) {
            if (employeeRepository.existsEmployeeByPassportId(updatedEmployee.getPassportId())) {
                throw new PassportIdDuplicateException("1004");
            }
        } else {
            updatedEmployee.setPassportId(readEmployee.getPassportId());
        }

        if (updatedEmployee.getPassportValidity() == null) {
            updatedEmployee.setPassportValidity(readEmployee.getPassportValidity());
        }

        updatedEmployee.setEmployeeId(id);
        updatedEmployee.setUser(readEmployee.getUser());
        employeeRepository.save(updatedEmployee);
        return true;
    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        return employeeDto;
    }
}
