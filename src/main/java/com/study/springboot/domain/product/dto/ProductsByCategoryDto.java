package com.study.springboot.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsByCategoryDto {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private String productImgUrl;
}
