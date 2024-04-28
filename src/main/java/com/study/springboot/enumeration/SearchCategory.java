package com.study.springboot.enumeration;

import lombok.Getter;

@Getter
public enum SearchCategory {
    PROD_NAME("product_name"), PROD_CODE("product_code"), PROD_CATEGORY("category");

    private String value;

    SearchCategory(String value){
        this.value = value;
    }

}
