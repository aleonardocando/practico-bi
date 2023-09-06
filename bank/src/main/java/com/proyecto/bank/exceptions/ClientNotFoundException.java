package com.proyecto.bank.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Integer idCliente) {
        super("Could not find the client: "+ idCliente);
    }

    public ClientNotFoundException(String identificacionCliente) {
        super("Could not find the client with id: "+ identificacionCliente);
    }
}
