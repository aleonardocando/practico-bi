package com.proyecto.bank.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Integer employeeId) {
        super("Could not find the employee: "+ employeeId);
    }

    public EmployeeNotFoundException(String employeeIdentification) {
        super("Could not find the client with id: "+ employeeIdentification);
    }
}
