package com.study.springboot.enumeration;

import lombok.Getter;

@Getter
public enum ProductCategory {
    BURGER_SET("BURGER_SET"),
    BURGER_SINGLE("BURGER_SINGLE"),
    HAPPY_MEAL("HAPPY_MEAL"),
    DRINK("DRINK"),
    DESSERT("DESSERT");

    String value;

    ProductCategory(String value){
        this.value = value;
    }
}
