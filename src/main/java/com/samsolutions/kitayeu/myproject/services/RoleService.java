package com.samsolutions.kitayeu.myproject.services;

import com.samsolutions.kitayeu.myproject.entities.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> getAllRoles();

    void deleteRole(int id);

    Role updateRole(Role role);

    Role getById(int id);
}
