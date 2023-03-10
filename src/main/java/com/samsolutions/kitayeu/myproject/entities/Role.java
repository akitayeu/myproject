package com.samsolutions.kitayeu.myproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    private Integer roleId;

    private String roleName;

    @ManyToMany(mappedBy = "role")
    private Set<Employee> employee;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
