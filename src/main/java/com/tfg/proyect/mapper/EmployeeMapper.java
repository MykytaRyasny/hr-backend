package com.tfg.proyect.mapper;

import com.tfg.proyect.dto.EmployeeDTO;
import com.tfg.proyect.model.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDTO employeeToEmployeeDTO(EmployeeEntity employee);

  EmployeeEntity employeeDTOToEmployee(EmployeeDTO employeeDTO);

  List<EmployeeDTO> employeeToEmployeeDTOList(List<EmployeeEntity> employees);

}
