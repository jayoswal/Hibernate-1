package com.jayoswal.hibernate_demo;


import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
