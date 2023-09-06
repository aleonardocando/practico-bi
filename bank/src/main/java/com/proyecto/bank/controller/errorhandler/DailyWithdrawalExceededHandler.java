package com.proyecto.bank.controller.errorhandler;

import com.proyecto.bank.exceptions.DailyWithdrawalExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DailyWithdrawalExceededHandler {
    @ResponseBody
    @ExceptionHandler(DailyWithdrawalExceededException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String retiroDiarioSuperadoHandler(DailyWithdrawalExceededException ex) {
        return ex.getMessage();
    }
}
