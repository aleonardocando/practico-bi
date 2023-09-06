package com.proyecto.bank.service.services.impl;

import com.proyecto.bank.controller.request.EmployeeRequest;
import com.proyecto.bank.exceptions.ClientNotFoundException;
import com.proyecto.bank.exceptions.EmployeeNotFoundException;
import com.proyecto.bank.persistence.entity.Client;
import com.proyecto.bank.persistence.entity.Employee;
import com.proyecto.bank.persistence.repository.EmployeeRepository;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.dto.EmployeeDTO;
import com.proyecto.bank.service.mapper.EmployeeMapper;
import com.proyecto.bank.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeRequest employeeRequest) {
        return EmployeeMapper.INSTANCE.employeeToEmployeeDTO(
                employeeRepository.save(
                        employeeMapper.INSTANCE.employeeRequestToEmployee(employeeRequest)));

    }

    @Override
    public List<EmployeeDTO> listEmployees() {
        List<EmployeeDTO> employeesDTOList= new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()
        ) {
            employeesDTOList.add(employeeMapper.INSTANCE.employeeToEmployeeDTO(employee));
        }
        return employeesDTOList;
    }

    @Override
    public EmployeeDTO findEmployee(Integer employeeId) {
        Optional<Employee> employeeResult = employeeRepository.findById(employeeId);
        if(employeeResult.isPresent()){
            return employeeMapper.INSTANCE.employeeToEmployeeDTO(employeeResult.get());
        }else{
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public EmployeeDTO replaceEmployee(EmployeeRequest employeeRequest, Integer employeeId) {
        Optional<Employee> employeeResult = employeeRepository.findById(employeeId);
        if(employeeResult.isPresent()){
            Employee employee = employeeMapper.INSTANCE.employeeRequestToEmployee(employeeRequest);
            employee.setId(employeeResult.get().getId());
            return employeeMapper.INSTANCE.employeeToEmployeeDTO(employeeRepository.save(employee));
        }else{
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public EmployeeDTO updateEmployee(Map<String, Object> employeeData, Integer employeeId) {
        Optional<Employee> employeeResult = Optional.ofNullable(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId)));
        if(employeeResult.isPresent()) {
            employeeData.forEach((clave, valor) -> {
                Field campo = ReflectionUtils.findField(Employee.class, clave);
                campo.setAccessible(true);
                ReflectionUtils.setField(campo, employeeResult.get(), valor);
            });
            return employeeMapper.INSTANCE.employeeToEmployeeDTO(employeeRepository.save(employeeResult.get()));
        }else{
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        if(employeeRepository.findById(employeeId).isPresent()) {
            employeeRepository.deleteById(employeeId);
        }else{
            throw new EmployeeNotFoundException(employeeId);
        }
    }


    @Override
    public EmployeeDTO findRoleByCodeEmployee(String code) {
        Optional<Employee> employeeResult = employeeRepository.findByCode(code);
        if(employeeResult.isPresent()) {
            return employeeMapper.INSTANCE.employeeToEmployeeDTO(employeeResult.get());
        }
        return null;
    }

    @Override
    public EmployeeDTO findEmployeeByCode(String code) {
        Optional<Employee> employeeResult = employeeRepository.findByCode(code);
        if(employeeResult.isPresent()) {
            return employeeMapper.INSTANCE.employeeToEmployeeDTO(employeeResult.get());
        }
        return null;
    }


}
