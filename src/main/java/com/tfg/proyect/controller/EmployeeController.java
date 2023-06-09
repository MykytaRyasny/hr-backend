package com.tfg.proyect.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.tfg.proyect.controller.utils.LoggerUtils;
import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.service.EmployeeService;
import com.tfg.proyect.utils.EmployeeCreatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Employee controller.
 */
@EnableMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Gets employee by id.
     *
     * @param dni  the dni
     * @param user the user, just for authorization
     * @return the employee by id
     */
    @GetMapping(value = "/find/{dni}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "dni") String dni, final Principal user) {
        try {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(dni);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets all.
     *
     * @param user the user, just for authorization
     * @return the list of all employees
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr', 'ROLE_shop_vendor', 'ROLE_shop_manager')")
    public ResponseEntity<List<EmployeeDTO>> getAll(final Principal user) {
        try {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * New employee response entity.
     *
     * @param employeeDTO the employee dto
     * @param user        the user, just for authorization
     * @return the response entity 201 if created
     */
    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<EmployeeDTO> newEmployee(@RequestBody EmployeeDTO employeeDTO, final Principal user) {
        try {
            EmployeeDTO employee = new EmployeeDTO();
            if (EmployeeCreatorUtils.validateDNI(employeeDTO.getDni())) {
                employee.setDni(employeeDTO.getDni());
            } else {
                LoggerUtils.LOGGER.warn("Invalid DNI");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            employee.setRole(employeeDTO.getRole());
            employee.setPassword(EmployeeCreatorUtils.generatePassword());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setUsername(EmployeeCreatorUtils.generateUsername(employeeDTO.getFirstName(), employeeDTO.getLastName()));
            employeeService.save(employee);
            LoggerUtils.LOGGER.info("Username created succesfully, the password is:{}", employee.getPassword());
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Internar error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete employee response entity.
     *
     * @param dni  the dni
     * @param user the user, just for authorization
     * @return the response entity, 200 if deleted
     */
    @DeleteMapping("/delete/{dni}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr')")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "dni") String dni, final Principal user) {
        try {
            employeeService.deleteEmployee(dni);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Edit employee response entity.
     *
     * @param employeeDTO the employee dto
     * @param user        the user, just for authorization
     * @return the response entity, 200 if edited
     */
    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hr')")
    public ResponseEntity<?> editEmployee(@RequestBody EmployeeDTO employeeDTO, final Principal user) {
        try {
            employeeService.updateEmployee(employeeDTO);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
