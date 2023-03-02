package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DtoToRoleConverter;
import com.samsolutions.kitayeu.myproject.converters.RoleToDtoConverter;
import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.exceptions.DeleteOrChangeEntityNotAllowException;
import com.samsolutions.kitayeu.myproject.exceptions.EntityDuplicateException;
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
        assert createdRole != null;
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
            if (employeeRepository.findEmployeesByRole(roleRepository.findById(id).orElse(null)).size() != 0) {
                List<Employee> employeeList = employeeRepository.findEmployeesByRole(roleRepository.findById(id).get());
                for (Employee employee : employeeList) {
                    if (employee.getRole().size() == 1) {
                        Set<Role> roleSet = new HashSet<>();
                        roleSet.add(roleRepository.findRoleByRoleName("EMPTY"));
                        employeeRepository.save(employee);
                    }
                }
                roleRepository.deleteById(id);
            }
            roleRepository.deleteById(id);
        } else {
            throw new DeleteOrChangeEntityNotAllowException("1001");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateRole(RoleDto roleDto, int id) {
        if (id != 0) {
            DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
            Role updatedRole = dtoToRoleConverter.convert(roleDto);
            assert updatedRole != null;
            if (roleRepository.existsRoleByRoleName(updatedRole.getRoleName())) {
                throw new EntityDuplicateException("1000");
            } else {
                roleDto.setRoleId(id);
                roleRepository.save(dtoToRoleConverter.convert(roleDto));
                return true;
            }
        } else {
            throw new DeleteOrChangeEntityNotAllowException("1001");
        }
    }

    @Override
    public RoleDto getById(int id) {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        return roleToDtoConverter.convert(roleRepository.getReferenceById(id));
    }
}
