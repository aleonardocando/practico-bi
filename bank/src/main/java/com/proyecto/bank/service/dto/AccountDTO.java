package com.proyecto.bank.service.dto;

import com.proyecto.bank.utils.enums.AccountTypeEnum;
import com.proyecto.bank.persistence.entity.Account;
import com.proyecto.bank.persistence.entity.Movement;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private int id;

    private String number;

    private AccountTypeEnum type;

    private int balance;

    private Boolean state;

    private List<Movement> movements;

    private int client;

    private Date date;

}
