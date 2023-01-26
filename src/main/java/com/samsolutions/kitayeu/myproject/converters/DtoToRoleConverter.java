package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Role;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class DtoToRoleConverter implements Converter<RoleDto, Role> {

    @Override
    public Role convert(@NonNull RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return role;
    }
}
