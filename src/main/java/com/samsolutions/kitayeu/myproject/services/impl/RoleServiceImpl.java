package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DtoToRoleConverter;
import com.samsolutions.kitayeu.myproject.converters.RoleToDtoConverter;
import com.samsolutions.kitayeu.myproject.dtos.RoleDto;

import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.repositories.RoleRepository;
import com.samsolutions.kitayeu.myproject.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public RoleDto createRoleDto(RoleDto roleDto) {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
        Role createdRole = roleRepository.save(dtoToRoleConverter.convert(roleDto));
        return roleToDtoConverter.convert(createdRole);
    }

    @Override
    public List<RoleDto> getAllRoleDtos() {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        List<RoleDto> roleDtoList = new ArrayList<>();
        List<Role> roleList = roleRepository.findAll();
        for (Role role : roleList) {
            roleDtoList.add(roleToDtoConverter.convert(role));
        }
        return roleDtoList;

    }

    @Override
    @Transactional
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RoleDto updateRoleDto(RoleDto roleDto) {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
        Role updatedRole = roleRepository.save(dtoToRoleConverter.convert(roleDto));
        return roleToDtoConverter.convert(updatedRole);
    }

    @Override
    public RoleDto getById(int id) {
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        return roleToDtoConverter.convert(roleRepository.getReferenceById(id));
    }
}
