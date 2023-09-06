package com.proyecto.bank.service.services;

import com.proyecto.bank.controller.request.ClientRequest;
import com.proyecto.bank.controller.request.EmployeeRequest;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeRequest employeeRequest);

    List<EmployeeDTO> listEmployees();

    EmployeeDTO findEmployee(Integer employeeId);

    EmployeeDTO replaceEmployee(EmployeeRequest employeeRequest, Integer employeeId);

    EmployeeDTO updateEmployee(Map<String, Object> employeeData, Integer employeeId);

    void deleteEmployee(Integer employeeId);


    EmployeeDTO findRoleByCodeEmployee(String code);

    EmployeeDTO findEmployeeByCode(String code);

}
