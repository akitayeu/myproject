package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DtoToRoleConverter;
import com.samsolutions.kitayeu.myproject.converters.RoleToDtoConverter;
import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.exceptions.DeleteOrChangeEntityNotAllowException;
import com.samsolutions.kitayeu.myproject.exceptions.EntityDuplicateException;
import com.samsolutions.kitayeu.myproject.exceptions.IdMismatchException;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import com.samsolutions.kitayeu.myproject.repositories.RoleRepository;
import com.samsolutions.kitayeu.myproject.services.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public RoleDto createRole(RoleDto roleDto) {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
        Role createdRole = dtoToRoleConverter.convert(roleDto);
        if (roleRepository.existsRoleByRoleName(createdRole.getRoleName())) {
            throw new EntityDuplicateException("1000");
        } else {
            roleRepository.save(dtoToRoleConverter.convert(roleDto));
            return roleToDtoConverter.convert(createdRole);
        }
    }

    @Override
    public List<RoleDto> getAllRole(int page) {
        Pageable paging = PageRequest.of(page, pageSize);
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        return roleRepository.findAll(paging).stream()
                .map(roleToDtoConverter::convert)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public boolean deleteRole(int id) {
        if (id != 0) {
            if (roleRepository.findById(id).isPresent()) {
                if (employeeRepository.findEmployeesByRole(roleRepository.findById(id).orElse(null)).size() != 0) {
                    List<Employee> employeeList = employeeRepository.findEmployeesByRole(roleRepository.findById(id).get());
                    for (Employee employee : employeeList) {
                        if (employee.getRole().size() == 1) {
                            Set<Role> roleSet = new HashSet<>();
                            roleSet.add(roleRepository.findRoleByRoleName("EMPTY"));
                            employee.setRole(roleSet);
                            employeeRepository.save(employee);
                        } else {
                            Set<Role> roleSetFromDb = employee.getRole();
                            Set<Role> roleSet = new HashSet<>();
                            for (Role role : roleSetFromDb) {
                                if (role.getRoleId() != id) {

                                    roleSet.add(role);
                                }
                            }
                            employee.setRole(roleSet);
                        }
                    }
                }
                roleRepository.deleteById(id);
            } else {
                return false;
            }
        } else {
            throw new DeleteOrChangeEntityNotAllowException("1001");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateRole(RoleDto roleDto, int id) {
        if (id != 0) {
            if (roleRepository.findById(id).isPresent()) {
                DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
                Role updatedRole = dtoToRoleConverter.convert(roleDto);
                if (updatedRole.getRoleId() != null) {
                    if (id != updatedRole.getRoleId()) { // ID mismatch
                        throw new IdMismatchException("1005");
                    }
                }
                if (roleRepository.existsRoleByRoleName(updatedRole.getRoleName())) {
                    throw new EntityDuplicateException("1000");
                } else {
                    updatedRole.setRoleId(id);
                    roleRepository.save(updatedRole);
                    return true;
                }
            } else {
                return false;
            }
        } else {
            throw new DeleteOrChangeEntityNotAllowException("1001");
        }
    }

    @Override
    public RoleDto getById(int id) {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        if (roleRepository.findById(id).isPresent()) {
            return roleToDtoConverter.convert(roleRepository.findById(id).get());
        } else {
            return null;
        }
    }
}
