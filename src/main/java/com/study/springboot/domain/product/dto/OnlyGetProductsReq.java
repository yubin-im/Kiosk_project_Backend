package com.study.springboot.domain.product.dto;

import lombok.Getter;

@Getter
public class OnlyGetProductsReq {
    private String category;
    private int page;
}
