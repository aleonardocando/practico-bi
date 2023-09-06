package com.proyecto.bank.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Integer idCuenta) {
        super("Could not find the account "+ idCuenta);
    }

    public AccountNotFoundException(String numeroCuenta) {
        super("Could not find the account number: "+ numeroCuenta);
    }
}
