package com.study.springboot.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecommendProductDto {
    private Long id;
    private String productName;
    private Integer productPrice;
    private String productImgUrl;
}
