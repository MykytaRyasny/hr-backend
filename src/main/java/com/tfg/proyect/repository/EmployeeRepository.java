package com.tfg.proyect.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.tfg.proyect.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

  Optional<EmployeeEntity> findByUsername(String username);

 // @Query(value = )

}
