package com.tfg.proyect.repository;

import com.tfg.proyect.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

  Optional<EmployeeEntity> findByUsername(String username);

}
