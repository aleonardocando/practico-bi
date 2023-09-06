package com.proyecto.bank.exceptions;

public class DailyWithdrawalExceededException extends RuntimeException{
    public DailyWithdrawalExceededException(String numeroCuenta){
        super("Daily withdrawal exceeded: "+ numeroCuenta);
    }
}
