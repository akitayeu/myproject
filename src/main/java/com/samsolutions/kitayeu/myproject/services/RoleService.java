package com.samsolutions.kitayeu.myproject.services;


import com.samsolutions.kitayeu.myproject.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> getAllRole(int page);

    boolean deleteRole(int id);

    boolean updateRole(RoleDto roleDto, int id);

    RoleDto getById(int id);
}
