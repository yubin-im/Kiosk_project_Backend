package com.study.springboot.enumeration;

public enum OrderListStatus {
    PREPARING("PREPARING"),
    COMPLETED("COMPLETED"),
    READY("READY"),
    RECEIVED("RECEIVED");

    String value;

    OrderListStatus(String value){
        this.value = value;
    }
}
