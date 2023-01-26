package com.samsolutions.kitayeu.myproject.repositories;


import com.samsolutions.kitayeu.myproject.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class RoleTest {

    @Autowired
    private RoleRepository roleRepository;

    private int id;

    @BeforeEach
    public void createSomething() {
        Role createdRole1 = new Role("Developer555");
        roleRepository.saveAndFlush(createdRole1);
        id = createdRole1.getRoleId();
        Role createdRole2 = new Role("Tester555");
        roleRepository.saveAndFlush(createdRole2);
    }

    @Test
    public void readUpdateSomething() {
        Role readRole = roleRepository.findById(id).get();
        assertEquals("Developer555", readRole.getRoleName());
        readRole.setRoleName("PM555");
        roleRepository.saveAndFlush(readRole);
    }

    @AfterEach
    public void deleteSomething() {
        Role deletedRole = roleRepository.findById(id).get();
        assertEquals("PM555", deletedRole.getRoleName());
        roleRepository.delete(deletedRole);
        assertEquals(1, roleRepository.findAll().size());
        roleRepository.deleteAll();
    }
}
