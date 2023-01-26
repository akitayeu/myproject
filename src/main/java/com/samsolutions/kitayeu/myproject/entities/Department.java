package com.samsolutions.kitayeu.myproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(name = "department_id_seq", sequenceName = "department_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq")
    private Integer departmentId;
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

}



