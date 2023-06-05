package com.tfg.proyect.controller;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.service.EmployeeService;
import com.tfg.proyect.utils.EmployeeCreatorUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@EnableMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = "/find/{dni}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "dni") String dni, final Principal user) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(dni);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr')")
    public ResponseEntity<List<EmployeeDTO>> getAll(final Principal user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentAuth = authentication.getAuthorities().toString();
        String currentUser = authentication.getName();
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
        LOGGER.info("Role of user {} with rol {}", currentUser, currentAuth);
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<EmployeeDTO> newEmployee(@RequestBody EmployeeDTO employeeDTO, final Principal user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentAuth = authentication.getAuthorities().toString();
        String currentUser = authentication.getName();
        try {
            EmployeeDTO employee = new EmployeeDTO();
            if (EmployeeCreatorUtils.validateDNI(employeeDTO.getDni())) {
                employee.setDni(employeeDTO.getDni());
            } else {
                LOGGER.warn("Invalid DNI");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            employee.setRole(employeeDTO.getRole());
            employee.setPassword(EmployeeCreatorUtils.generatePassword());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setUsername(EmployeeCreatorUtils.generateUsername(employeeDTO.getFirstName(), employeeDTO.getLastName()));
            employeeService.save(employee);
            LOGGER.info("Username created succesfully, the password is:{}", employee.getPassword());
            LOGGER.info("Role of user {} with rol {}", currentUser, currentAuth);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("Internar error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{dni}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr')")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "dni") String dni, final Principal user) {
        try {
            employeeService.deleteEmployee(dni);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr')")
    public ResponseEntity<?> editEmployee(@RequestBody EmployeeDTO employeeDTO, final Principal user) {
        try {
            employeeService.updateEmployee(employeeDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
