package com.samsolutions.kitayeu.myproject.repositories;

import com.samsolutions.kitayeu.myproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
