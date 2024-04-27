package com.study.springboot.enumeration.error;


import lombok.Getter;

@Getter
public enum StatusCode {

    ADMIN_NOT_FOUND("ENF00"),
    USER_NOT_FOUND("ENF01"),


    USER_LOGIN("UR01"),
    USER_LOGIN_NOT_FOUND("EUL00"),
    USER_LOGIN_PW_INVALID("EUL01"),
    USER_REG_ID_EXISTS("EUR01"),
    USER_REG_PW_INVALID("EUR02"),
    USER_REG_SUCCESS("UR00"),



    ADMIN_LOGIN("AD01"),
    ADMIN_PW_INVALID("EAL01")
    ;


    private String value;
    StatusCode(String value){
        this.value = value;
    }
}
