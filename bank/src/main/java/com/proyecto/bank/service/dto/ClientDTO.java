package com.proyecto.bank.service.dto;

import com.proyecto.bank.persistence.entity.Account;
import com.proyecto.bank.persistence.entity.Client;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private int id;
    private String code;

    private String firstName;

    private String lastName;

    private String gender;

    private Integer age;

    private String identification;

    private String address;

    private String phone;

    private String password;

    private Boolean state;

    private List<Account> accounts;
}
