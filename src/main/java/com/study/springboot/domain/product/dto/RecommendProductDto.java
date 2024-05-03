package com.study.springboot.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecommendProductDto {
    private String productCode;
    private String productName;
    private Integer productPrice;
    private String productImgUrl;
}
