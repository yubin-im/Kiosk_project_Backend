package com.study.springboot.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productCode;
    private String productName;
    private Integer productPrice;
}
