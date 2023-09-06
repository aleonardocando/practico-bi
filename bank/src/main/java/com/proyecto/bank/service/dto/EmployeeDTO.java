package com.proyecto.bank.service.dto;

import com.proyecto.bank.persistence.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

    private Integer Id;
    private String code;

    private String firstName;

    private String lastName;
    private String gender;
    private Integer age;

    private String identification;

    private String address;

    private String phone;

    private String role;

    private List<Account> accounts;

}
