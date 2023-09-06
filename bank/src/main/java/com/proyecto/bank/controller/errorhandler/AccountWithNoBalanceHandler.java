package com.proyecto.bank.controller.errorhandler;

import com.proyecto.bank.exceptions.AccountWithNoBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountWithNoBalanceHandler {
    @ResponseBody
    @ExceptionHandler(AccountWithNoBalanceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String cuentaSinSaldoHandler(AccountWithNoBalanceException ex) {
        return ex.getMessage();
    }
}
