package com.proyecto.bank.service.mapper;

import com.proyecto.bank.controller.request.ClientRequest;
import com.proyecto.bank.controller.request.EmployeeRequest;
import com.proyecto.bank.persistence.entity.Client;
import com.proyecto.bank.persistence.entity.Employee;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);
}
