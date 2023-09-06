package com.proyecto.bank.controller.errorhandler;

import com.proyecto.bank.exceptions.MovementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovementNotFoundHandler {
    @ResponseBody
    @ExceptionHandler(MovementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String movimientoNotFoundHandler(MovementNotFoundException ex) {
        return ex.getMessage();
    }
}
