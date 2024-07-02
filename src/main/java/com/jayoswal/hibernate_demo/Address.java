package com.jayoswal.hibernate_demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="address_generator")
    @SequenceGenerator(name = "address_generator", sequenceName = "address_seq", allocationSize = 1)
    private int addressId;

    @Column(name = "address")
    private String address;

    @OneToOne
    private Employee employee;
}
