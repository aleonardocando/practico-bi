package com.proyecto.bank.exceptions;

public class MovementNotFoundException extends RuntimeException {
    public MovementNotFoundException(Integer idMovimiento) {
        super("Could not find the movement: "+ idMovimiento);
    }
}
