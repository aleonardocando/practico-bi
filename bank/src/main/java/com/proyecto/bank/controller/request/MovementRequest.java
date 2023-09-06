package com.proyecto.bank.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovementRequest {

    private String identificacionCliente;

    private String numeroCuenta;

    private int value;

    private String activity;


}
