package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.sun.istack.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class RoleToDtoConverter implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(@NotNull Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
}
