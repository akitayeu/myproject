package com.samsolutions.kitayeu.myproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id

    private int userId;
    private String userName;
    private String userPassword;
    private String userMail;

    @OneToOne (fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

    public User(String userName, String userPassword, String userMail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userMail = userMail;
    }
}
