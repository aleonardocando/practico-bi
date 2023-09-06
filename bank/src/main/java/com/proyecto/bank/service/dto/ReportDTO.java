package com.proyecto.bank.service.dto;

import com.proyecto.bank.persistence.entity.Account;
import com.proyecto.bank.persistence.entity.Movement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {

    private String firstName;

    private String lastName;

    private String role;

    private Integer age;

    private Date date;

    private String account;

    private List<Movement> movements;

    private Double balance;

    public ReportDTO(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.role = employeeDTO.getRole();
        this.age = employeeDTO.getAge();
    }

    public ReportDTO (ClientDTO clientDTO){
        this.firstName = clientDTO.getFirstName();
        this.lastName = clientDTO.getLastName();
        this.age = clientDTO.getAge();

    }
}
