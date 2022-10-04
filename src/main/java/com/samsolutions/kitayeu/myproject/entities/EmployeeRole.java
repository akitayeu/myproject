package com.samsolutions.kitayeu.myproject.entities;

import javax.persistence.*;

@Entity
@Table(name="employee_role")
public class EmployeeRole {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int counter;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Role role;

    public EmployeeRole() {
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
