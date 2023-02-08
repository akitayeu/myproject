package com.samsolutions.kitayeu.myproject.repositories;


import com.samsolutions.kitayeu.myproject.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class RoleTest {

    @Autowired
    private RoleRepository roleRepository;

    private int id1;
    private int id2;

    @BeforeEach
    public void createSomething() {
        Role createdRole1 = new Role("Developer555");
        roleRepository.saveAndFlush(createdRole1);
        id1 = createdRole1.getRoleId();
        Role createdRole2 = new Role("Tester555");
        roleRepository.saveAndFlush(createdRole2);
        id2 = createdRole2.getRoleId();
    }

    @Test
    public void readUpdateSomething() {
        Role readRole = roleRepository.findById(id1).get();
        assertEquals("Developer555", readRole.getRoleName());
        readRole.setRoleName("PM555");
        roleRepository.saveAndFlush(readRole);
    }

    @AfterEach
    public void deleteSomething() {
        Role deletedRole = roleRepository.findById(id1).get();
        Role deletedRole1 = roleRepository.findById(id2).get();
        roleRepository.delete(deletedRole);
        roleRepository.delete(deletedRole1);
        assertTrue(roleRepository.findById(id1).isEmpty());
        assertTrue(roleRepository.findById(id2).isEmpty());
    }
}
