package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.config.PasswordEncoder;
import com.samsolutions.kitayeu.myproject.converters.*;
import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.entities.User;
import com.samsolutions.kitayeu.myproject.exceptions.*;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import com.samsolutions.kitayeu.myproject.repositories.RoleRepository;
import com.samsolutions.kitayeu.myproject.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

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
        DtoToEmployeeConverter dtoToEmployeeConverter = new DtoToEmployeeConverter();
        Employee createdEmployee = dtoToEmployeeConverter.convert(employeeDto);

        if (createdEmployee == null) {
            throw new InvalidDataExceptionException("1012");
        }

        if (createdEmployee.getFirstname() == null) {
            throw new InvalidDataExceptionException("1012");
        }

        if (createdEmployee.getLastname() == null) {
            throw new InvalidDataExceptionException("1012");
        }

        if (createdEmployee.getBirthdate() == null) {
            throw new InvalidDataExceptionException("1012");
        }

        if (String.valueOf(createdEmployee.getGender()).equalsIgnoreCase("m")) {
            createdEmployee.setGender('m');
        } else if (String.valueOf(createdEmployee.getGender()).equalsIgnoreCase("w")) {
            createdEmployee.setGender('w');
        } else {
            throw new GenderWrongException("1011");
        }

        if (createdEmployee.getPassportId() == null) {
            throw new InvalidDataExceptionException("1012");
        } else {
            if (employeeRepository.existsEmployeeByPassportId(createdEmployee.getPassportId())) {
                throw new PassportIdDuplicateException("1004");
            }
        }

        if (createdEmployee.getPassportValidity() == null) {
            throw new InvalidDataExceptionException("1012");
        }

        if (employeeDto.getDepartmentDto() == null) {
            createdEmployee.setDepartment(departmentRepository.findById(0).get());
        } else {
            DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
            Department receiveDepartment = dtoToDepartmentConverter.convert(employeeDto.getDepartmentDto());
            if (!departmentRepository.existsByDepartmentName(receiveDepartment.getDepartmentName())) {
                throw new DepartmentNotFoundException("1002");
            }
            createdEmployee.setDepartment(receiveDepartment);
        }

        if (employeeDto.getRoleDtoSet() == null) {
            Set<Role> setRoles = new HashSet<>();
            setRoles.add(roleRepository.findById(0).get());
            createdEmployee.setRole(setRoles);
        } else {
            DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
            Set<Role> receiveRoles = new HashSet<>();
            for (RoleDto dtoRole : employeeDto.getRoleDtoSet()) {
                if (!roleRepository.existsRoleByRoleName(dtoToRoleConverter.convert(dtoRole).getRoleName())) {
                    throw new RoleNotFoundException("1010");
                } else {
                    receiveRoles.add(dtoToRoleConverter.convert(dtoRole));
                }
            }
            createdEmployee.setRole(receiveRoles);
        }

        if (employeeDto.getUserDto() == null) {
            throw new InvalidDataExceptionException("1012");
        } else {
            DtoToUserConverter dtoToUserConverter = new DtoToUserConverter();
            User receiveUser = dtoToUserConverter.convert(employeeDto.getUserDto());
            if (receiveUser.getUserName() == null) {
                throw new InvalidDataExceptionException("1012");
            } else {
                if (userRepository.existsUserByUserName(receiveUser.getUserName())) {
                    throw new UserNameDuplicateException("1007");
                }
            }
            if (receiveUser.getUserMail() == null) {
                throw new InvalidDataExceptionException("1012");
            } else {
                if (userRepository.existsUserByUserMail(receiveUser.getUserMail())) {
                    throw new UserMailDuplicateException("1006");
                }
            }
            if (employeeDto.getUserDto().getUserPassword() == null || employeeDto.getUserDto().getUserPassword().isBlank()) {
                throw new InvalidDataExceptionException("1012");
            } else {
              receiveUser.setUserPasswordHash(encoder.encode(employeeDto.getUserDto().getUserPassword()));
            }
            createdEmployee.setUser(receiveUser);
        }
        employeeRepository.save(createdEmployee);
        return employeeDto;
    }
}
