package com.tfg.proyect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.proyect.model.Employee;
import com.tfg.proyect.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    /*
     * @GetMapping("{id}")
     * public ResponseEntity<Employee> getEmployeeById(
     * 
     * @PathVariable(name = "id") String dni) {
     * Employee emp = employeeService.getEmployeeById(dni);
     * return new ResponseEntity<>(emp, HttpStatus.OK);
     * }
     */

    @GetMapping(value = "{id}")
    public Employee getEmployeeById(
            @PathVariable(value = "id") String dni) {
        return employeeService.getEmployeeById(dni);
    }

    @GetMapping("all")
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

}
