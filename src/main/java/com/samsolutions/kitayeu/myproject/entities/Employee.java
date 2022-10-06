package com.samsolutions.kitayeu.myproject.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int employeeId;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private char gender;
    private String passportId;
    private LocalDate passportValidity;
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "employee_role",joinColumns = @JoinColumn (name ="employee_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> role;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_Id")
    private Department department;

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public LocalDate getPassportValidity() {
        return passportValidity;
    }

    public void setPassportValidity(LocalDate passportValidity) {
        this.passportValidity = passportValidity;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}




