package com.proyecto.bank.controller.errorhandler;

import com.proyecto.bank.exceptions.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientNotFoundHandler {
    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clienteNotFoundHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }
}
