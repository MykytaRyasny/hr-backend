package com.tfg.proyect.service;

import java.util.List;

import com.tfg.proyect.model.Employee;

public interface EmployeeService {

    Employee getEmployeeById(String dni);

    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(String dni);

}
