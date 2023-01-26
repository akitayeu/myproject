package com.samsolutions.kitayeu.myproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "employee_id")
    private Integer userId;
    private String userName;
    @Column(name = "user_password")
    private String userPasswordHash;
    private String userMail;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    Employee employee;

    public User(String userName, String userMail, String userPasswordHash) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPasswordHash = userPasswordHash;
    }
}
