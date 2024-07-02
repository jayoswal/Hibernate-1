package com.jayoswal.hibernate_demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="employee_generator")
    @SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1)
    private int employeeId;

    @Column(name = "name")
    private String name;
}
