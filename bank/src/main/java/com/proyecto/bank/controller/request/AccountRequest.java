package com.proyecto.bank.controller.request;

import com.proyecto.bank.utils.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private String identificacionCliente;

    private String number;

    private AccountTypeEnum type;

    private int balance;

}
