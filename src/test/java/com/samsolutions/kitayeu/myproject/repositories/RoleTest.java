package com.samsolutions.kitayeu.myproject.repositories;


import com.samsolutions.kitayeu.myproject.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class RoleTest {

    @Autowired
    private RoleRepository roleRepository;

    private int id;

    @BeforeEach
    public void createAnything() {
        Role createdRole1 = new Role("Developer");
        roleRepository.saveAndFlush(createdRole1);
        id = createdRole1.getRoleId();
        Role createdRole2 = new Role("Tester");
        roleRepository.saveAndFlush(createdRole2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void readUpdateAnything() {
        Role readedRole = roleRepository.getReferenceById(id);
        assertEquals("Developer", readedRole.getRoleName());
        readedRole.setRoleName("PM");
        roleRepository.saveAndFlush(readedRole);
    }

    @AfterEach
    public void deleteAnything() {
        Role deletedRole = roleRepository.getReferenceById(id);
        assertEquals("PM", deletedRole.getRoleName());
        roleRepository.delete(deletedRole);
        assertEquals(1, roleRepository.findAll().size());
    }
}
