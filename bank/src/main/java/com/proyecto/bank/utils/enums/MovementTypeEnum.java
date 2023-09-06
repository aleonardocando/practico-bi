package com.proyecto.bank.utils.enums;

public enum MovementTypeEnum {
    Credit("D"),
    Withdrawal("R");

    private final String code;

    MovementTypeEnum(String code){
        this.code = code;
    }

    public String code(){
        return code;
    }


}
