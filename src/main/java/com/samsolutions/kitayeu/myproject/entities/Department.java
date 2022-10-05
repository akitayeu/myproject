package com.samsolutions.kitayeu.myproject.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int departmentId;
    private String departmentName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Set<Employee> employeeSet;

    public Department() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}



