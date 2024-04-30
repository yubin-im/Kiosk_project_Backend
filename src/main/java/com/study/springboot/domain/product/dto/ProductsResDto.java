package com.study.springboot.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductsResDto {
    private List<ProductsByCategoryDto> productDtos;
    private String userName;
    private Integer orderListTotalAmount;
    private Integer orderListTotalPrice;
}
