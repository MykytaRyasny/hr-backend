package com.tfg.proyect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.proyect.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
