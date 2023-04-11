package com.tfg.proyect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.tfg.proyect.model.Employee;
import com.tfg.proyect.repository.EmployeeRepository;
import com.tfg.proyect.service.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    /*
     * All tests should be
     * Given - When - Then
     */

    @Test
    @Rollback(true)
    public void insertAnEmployeeTest() {
        // Given
        employeeRepository.save(new Employee("81305842Z", "Mykyta", "Ryasny", "nekit", "123", "admin"));
        // When
        Employee emp = employeeRepository.findById("81305842Z").get();
        // Then
        assertEquals("81305842Z", emp.getDni());

    }

    @Test
    public void listAllEmployeesTest() {
        // Given
        Employee emp1 = new Employee("05309740Y", "Perla Mar", "Barrera", "Perlaba", "123", "user");
        Employee emp2 = new Employee("91869584V", "Leire", "Diéguez", "Leiredi", "123", "user");
        Employee emp3 = new Employee("57256365G", "Diéguez", "Diéguez", "Dieguezdi", "123", "user");

        // When

    }
}
