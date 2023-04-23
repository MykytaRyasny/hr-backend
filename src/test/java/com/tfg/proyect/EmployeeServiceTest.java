package com.tfg.proyect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.tfg.proyect.model.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.mapper.EmployeeMapper;
import com.tfg.proyect.repository.EmployeeRepository;
import com.tfg.proyect.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Spy
    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    private EmployeeEntity employee;

    @BeforeEach
    public void setup() {
        employee = new EmployeeEntity("81305842Z", "Mykyta", "Ryasny", "nekit", "123", "admin");
    }

    /*
     * All tests should be
     * Given - When - Then
     */

    @DisplayName("Test for find by ID")
    @Test
    public void insertAnEmployeeTest() {
        // Given
        when(employeeRepository.findById("81305842Z")).thenReturn(Optional.of(employee));
        // When
        EmployeeDTO employeeDTO = employeeServiceImpl.getEmployeeById("81305842Z");
        // Then
        assertEquals("81305842Z", employeeDTO.getDni());

    }
}
