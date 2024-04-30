package com.study.springboot.domain.product.dto;

import lombok.Getter;

@Getter
public class ProductsReqDto {
    private String category;
    private int page;
    private Long orderListId;
}
