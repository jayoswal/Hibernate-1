package com.jayoswal.hibernate_demo;


import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Employee, Integer> {

}
