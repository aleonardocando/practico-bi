package com.proyecto.bank.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String code;

    private String firstName;

    private String lastName;

    private String gender;

    private Integer age;

    private String identification;

    private String address;

    private String phone;

    private String role;
}
