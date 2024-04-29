package com.study.springboot.enumeration;

import lombok.Getter;

@Getter
public enum ProductCategory {
    BURGER_SET("BURGER_SET"),
    BURGER_SINGLE("BUGER_SIGNLE"),
    HAPPY_MEAL("HAPPY_MEAL"),
    DRINK("DRINK"),
    DESSERT("DESSERT");

    String value;

    ProductCategory(String value){
        this.value = value;
    }
}
