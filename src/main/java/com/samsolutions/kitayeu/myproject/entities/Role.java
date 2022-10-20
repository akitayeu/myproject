package com.samsolutions.kitayeu.myproject.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int roleId;
    private String roleName;
    @ManyToMany(mappedBy = "role")
    private Set<Employee> employee;

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }
}
