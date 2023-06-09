package com.tfg.proyect.service;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.model.EmployeeEntity;

import java.util.List;
import java.util.Optional;

/**
 * The interface Employee service.
 */
public interface EmployeeService {

  /**
   * Find by username optional.
   *
   * @param username the username
   * @return the optional
   */
  Optional<EmployeeEntity> findByUsername(String username);

  /**
   * Gets employee by id.
   *
   * @param dni the dni
   * @return the employee by id
   */
  EmployeeDTO getEmployeeById(String dni);

  /**
   * Save employee dto.
   *
   * @param employee the employee
   * @return the employee dto
   */
  EmployeeDTO save(EmployeeDTO employee);

  /**
   * Gets all employees.
   *
   * @return the all employees
   */
  List<EmployeeDTO> getAllEmployees();

  /**
   * Update employee employee dto.
   *
   * @param employeeDTO the employee dto
   * @return the employee dto
   */
  EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

  /**
   * Delete employee.
   *
   * @param dni the dni
   */
  void deleteEmployee(String dni);

}
