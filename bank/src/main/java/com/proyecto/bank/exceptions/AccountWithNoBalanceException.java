package com.proyecto.bank.exceptions;

public class AccountWithNoBalanceException extends RuntimeException {
    public AccountWithNoBalanceException(String numeroCuenta) {
        super("Unavailable balcance in account: "+numeroCuenta);
    }
}
