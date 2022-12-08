package com.samsolutions.kitayeu.myproject.services;


import com.samsolutions.kitayeu.myproject.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRoleDto(RoleDto roleDto);

    List<RoleDto> getAllRoleDtos();

    void deleteRole(int id);

    RoleDto updateRoleDto(RoleDto roleDto);

    RoleDto getById(int id);
}
