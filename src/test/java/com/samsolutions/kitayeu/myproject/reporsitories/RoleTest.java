package com.samsolutions.kitayeu.myproject.reporsitories;


import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.repositories.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class RoleTest {

    @Autowired
    private RoleRepository roleRepository;
    private Role role;

    @BeforeEach
    public void createAnything() {
        Role createdRole = new Role("Developer");
        role = roleRepository.saveAndFlush(createdRole);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void readUpdateAnything() {
    Role readedRole = roleRepository.getReferenceById(role.getRoleId());
    assertEquals ("Developer",readedRole.getRoleName());
    readedRole.setRoleName("PM");
    roleRepository.saveAndFlush(readedRole);
    }

    @AfterEach
    public void deleteAnything (){
        Role readedRole = roleRepository.getReferenceById(role.getRoleId());
        roleRepository.delete(readedRole);
        List<Role> roleList = roleRepository.findAll();
    }
}
