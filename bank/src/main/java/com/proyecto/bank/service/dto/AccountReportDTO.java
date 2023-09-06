package com.proyecto.bank.service.dto;

import com.proyecto.bank.utils.enums.AccountTypeEnum;

public class AccountReportDTO {
    private String identificacionCliente;

    private String codigoCliente;
    private String nombreCliente;

    private String apellidoCliente;

    private String numero;

    private AccountTypeEnum tipo;

    private int saldo;

    private int valorDebitos;

    private int valorCreditos;

    public AccountReportDTO() {
    }

    public AccountReportDTO(String identificacionCliente, String codigoCliente,String nombreCliente,String apellidoCliente, String numero, AccountTypeEnum tipo, int saldo, int valorDebitos, int valorCreditos) {
        this.identificacionCliente = identificacionCliente;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.valorDebitos = valorDebitos;
        this.valorCreditos = valorCreditos;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public AccountTypeEnum getTipo() {
        return tipo;
    }

    public void setTipo(AccountTypeEnum tipo) {
        this.tipo = tipo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getValorDebitos() {
        return valorDebitos;
    }

    public void setValorDebitos(int valorDebitos) {
        this.valorDebitos = valorDebitos;
    }

    public int getValorCreditos() {
        return valorCreditos;
    }

    public void setValorCreditos(int valorCreditos) {
        this.valorCreditos = valorCreditos;
    }
}
