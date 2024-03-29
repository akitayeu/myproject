package com.samsolutions.kitayeu.myproject;


import com.samsolutions.kitayeu.myproject.config.PasswordEncoder;
import com.samsolutions.kitayeu.myproject.entities.User;
import com.samsolutions.kitayeu.myproject.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class MyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminPassword() {
        if (userRepository.findById(0).isPresent() && userRepository.findById(0).get().getUserPasswordHash().isBlank()) {
            User userAdmin = userRepository.findById(0).get();
            final int LENGTH = 10;
            boolean useLetters = true;
            boolean useNumbers = true;
            String generatedPassword = RandomStringUtils.random(LENGTH, useLetters, useNumbers);
            System.out.println("Remember generated password for user admin: " + generatedPassword);
            String passwordHash = passwordEncoder.encode(generatedPassword);
            userAdmin.setUserPasswordHash(passwordHash);
            userRepository.saveAndFlush(userAdmin);
        }
    }

}


