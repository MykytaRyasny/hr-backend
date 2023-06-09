package com.tfg.proyect.repository;

import com.tfg.proyect.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Employee repository.
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    /**
     * Find by username optional. Needed for proper work of UserDetails
     *
     * @param username the username
     * @return the optional
     */
    Optional<EmployeeEntity> findByUsername(String username);

}
