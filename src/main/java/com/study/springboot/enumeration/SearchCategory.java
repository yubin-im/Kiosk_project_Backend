package com.study.springboot.enumeration;

import lombok.Getter;

@Getter
public enum SearchCategory {
    ENTIRE("ENTIRE", "ENTIRE", "ENTIRE"),
    NAME("product_name", "item_name", "user_name"),
    CODE("product_code", "item_code", "user_id"),
    CATEGORY("category", "category", "category");

    private String productValue;
    private String itemValue;
    private String userValue;

    SearchCategory(String productValue, String itemValue, String userValue){
        this.productValue = productValue;
        this.itemValue = itemValue;
        this.userValue = userValue;
    }

}
