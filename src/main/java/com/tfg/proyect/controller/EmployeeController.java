package com.tfg.proyect.controller;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

  @GetMapping(value = "{id}")
  @PreAuthorize("hasAnyRole('admin')")
  public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") String dni, final Principal user) {
    EmployeeDTO employeeDTO = employeeService.getEmployeeById(dni);
    return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
  }

  @GetMapping("all")
  public ResponseEntity<List<EmployeeDTO>> getAll() {
    List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
    return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
  }

  @PostMapping("new")
  public ResponseEntity<EmployeeDTO> newEmployee(@RequestBody EmployeeDTO employeeDTO) {
    try {
      employeeService.save(employeeDTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DataIntegrityViolationException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      LOGGER.error("Internar error", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
