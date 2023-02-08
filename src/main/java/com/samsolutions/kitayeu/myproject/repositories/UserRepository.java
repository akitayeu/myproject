package com.samsolutions.kitayeu.myproject.repositories;

import com.samsolutions.kitayeu.myproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String userName);

    Boolean existsUserByUserName(String userName);

    User findUserByUserMail (String userMail);

    Boolean existsUserByUserMail (String userMail);


}
