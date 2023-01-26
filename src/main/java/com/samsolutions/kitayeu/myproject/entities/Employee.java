package com.samsolutions.kitayeu.myproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    private Integer employeeId;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private char gender;
    private String passportId;
    private LocalDate passportValidity;
    @ManyToMany
    @JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_Id")
    private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    public Employee(String firstname, String lastname, LocalDate birthdate, char gender, String passportId,
                    LocalDate passportValidity) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.passportId = passportId;
        this.passportValidity = passportValidity;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.setEmployee(this);
    }

}




