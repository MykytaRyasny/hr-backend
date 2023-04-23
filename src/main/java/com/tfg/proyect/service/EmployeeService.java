package com.tfg.proyect.service;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.model.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  Optional<EmployeeEntity> findByUsername(String username);

  EmployeeDTO getEmployeeById(String dni);

  EmployeeDTO save(EmployeeDTO employee);

  List<EmployeeDTO> getAllEmployees();

  EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String password);

  void deleteEmployee(String dni);

}
