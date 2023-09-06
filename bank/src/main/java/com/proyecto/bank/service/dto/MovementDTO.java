package com.proyecto.bank.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementDTO {
    private int id;

    private Date date;

    private String movement;

    private String movementType;

    private int value;

    private int balance;

    private int account;

    private String activity;
}
