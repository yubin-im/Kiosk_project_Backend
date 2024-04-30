package com.study.springboot.enumeration.error;


import lombok.Getter;

@Getter
public enum StatusCode {

    ADMIN_NOT_FOUND("ENF00"),
    USER_NOT_FOUND("ENF01"),
    ADMIN_NO_PERMISSION("ENP00"),
    USER_NO_PERMISSION("ENP00"),


    USER_LOGIN("UR01"),
    USER_LOGIN_NOT_FOUND("EUL00"),
    USER_LOGIN_PW_INVALID("EUL01"),
    USER_REG_ID_EXISTS("EUR01"),
    USER_REG_PW_INVALID("EUR02"),
    USER_REG_SUCCESS("UR00"),
    USER_REG_FAILED("EUR00"),



    ADMIN_LOGIN("AD01"),
    ADMIN_PW_INVALID("EAL01"),



    PRODUCT_REMOVE_SUCCESS("PR00"),
    PRODUCT_REMOVE_FAIL("EPR00"),
    PRODUCT_NOT_FOUND("EPR01"),
    PRODUCT_CODE_MISMATCH("EPR02"),
    PRODUCT_EDIT_SUCCESS("PE00")

    ;


    private String value;
    StatusCode(String value){
        this.value = value;
    }
}
