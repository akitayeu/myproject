package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoleConverterTest {

    @Test
    public void toEntity() {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(1);
        roleDto.setRoleName("Role1");
        DtoToRoleConverter dtoToRoleConverter = new DtoToRoleConverter();
        Role role = dtoToRoleConverter.convert(roleDto);
        assert role != null;
        assertEquals(role.getRoleId(), roleDto.getRoleId());
        assertEquals(role.getRoleName(), roleDto.getRoleName());
    }

    @Test
    public void toDto() {
        Role role = new Role();
        role.setRoleId(2);
        role.setRoleName("Role2");
        RoleToDtoConverter roleToDtoConverter = new RoleToDtoConverter();
        RoleDto roleDto = roleToDtoConverter.convert(role);
        assert roleDto != null;
        assertEquals(roleDto.getRoleId(), role.getRoleId());
        assertEquals(roleDto.getRoleName(), role.getRoleName());
    }
}
