package com.study.springboot.domain.product.dto;

import com.study.springboot.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RecommendProductDto {
    private String productCode;
    private String productName;
    private Integer productPrice;
    private String productImgUrl;


    @Builder
    public RecommendProductDto(String productCode, String productName, Integer productPrice, String productImgUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImgUrl = productImgUrl;
    }

    public RecommendProductDto(Product entity){
        this.productCode = entity.getProductCode();
        this.productName = entity.getProductName();
        this.productPrice = entity.getProductPrice();
        this.productImgUrl = entity.getProductImgUrl();
    }
}
