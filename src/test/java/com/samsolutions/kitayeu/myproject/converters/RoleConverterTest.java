package com.samsolutions.kitayeu.myproject.converters;

import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.entities.Role;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

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
        assertEquals(roleDto.getRoleId(), role.getRoleId());
        assertEquals(roleDto.getRoleName(), role.getRoleName());
    }
}
