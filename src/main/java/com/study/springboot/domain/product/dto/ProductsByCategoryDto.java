package com.study.springboot.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsByCategoryDto {
    private String productCode;
    private String productName;
    private Integer productPrice;
    private String productImgUrl;
}
