package com.tfg.proyect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.proyect.model.Employee;
import com.tfg.proyect.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(String dni) {
        return employeeRepository.findById(dni).get();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employeeToEdit = employeeRepository.getReferenceById(employee.getDni());
        employeeToEdit.setFirstName(employee.getFirstName());
        employeeToEdit.setLastName(employee.getLastName());
        employeeToEdit.setLogin(employee.getLogin());
        employeeToEdit.setPassword(employee.getPassword());
        employeeToEdit.setUsername(employee.getUsername());
        Employee newEmployee = employeeRepository.save(employeeToEdit);
        return newEmployee;
    }

    @Override
    public void deleteEmployee(String dni) {
        employeeRepository.deleteById(dni);
    }

}
