package com.tfg.proyect.mapper;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.model.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface Employee mapper.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**
     * Pass EmployeeEntity to EmployeeDTO
     *
     * @param employee the employee
     * @return the employee dto
     */
    EmployeeDTO employeeToEmployeeDTO(EmployeeEntity employee);

    /**
     * Pass EmployeeDTO to EmployeeEntity
     *
     * @param employeeDTO the employee dto
     * @return the employee entity
     */
    EmployeeEntity employeeDTOToEmployee(EmployeeDTO employeeDTO);

    /**
     * Pass EmployeeEntity list to EmployeeDTO list
     *
     * @param employees the employees
     * @return the list
     */
    List<EmployeeDTO> employeeToEmployeeDTOList(List<EmployeeEntity> employees);

}
