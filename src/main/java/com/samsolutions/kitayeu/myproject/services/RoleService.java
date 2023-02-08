package com.samsolutions.kitayeu.myproject.services;


import com.samsolutions.kitayeu.myproject.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> getAllRole();

    void deleteRole(int id);

    RoleDto updateRole(RoleDto roleDto);

    RoleDto getById(int id);
}
