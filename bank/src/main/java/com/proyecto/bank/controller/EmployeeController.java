package com.proyecto.bank.controller;

import com.proyecto.bank.controller.request.ClientRequest;
import com.proyecto.bank.controller.request.EmployeeRequest;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.dto.EmployeeDTO;
import com.proyecto.bank.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listEmployees(){
        return new ResponseEntity<>(employeeService.listEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> findEmployee(@PathVariable Integer employeeId){
        return new ResponseEntity<>(employeeService.findEmployee(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.createEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> replaceEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Integer employeeId){
        return new ResponseEntity<>(employeeService.replaceEmployee(employeeRequest, employeeId), HttpStatus.OK);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody Map<String, Object> employeeData, @PathVariable Integer employeeId){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeData, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId){
        try{
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok().build();
        }catch(EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    }
}
