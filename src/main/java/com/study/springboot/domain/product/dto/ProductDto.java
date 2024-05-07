package com.study.springboot.domain.product.dto;


import com.study.springboot.domain.product.Product;
import com.study.springboot.enumeration.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
    private Long id;
    private String productName;

    private Integer productPrice;

    private String productCode;

    private String productImgUrl;

    private ProductCategory category;


    @Builder
    public ProductDto(String productName, Integer productPrice, String productCode, String productImgUrl, ProductCategory category) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCode = productCode;
        this.productImgUrl = productImgUrl;
        this.category = category;
    }

    public ProductDto(Product entity){
        this.id = entity.getId();
        this.productName = entity.getProductName();
        this.productPrice = entity.getProductPrice();
        this.productCode = entity.getProductCode();
        this.productImgUrl = entity.getProductImgUrl();
        this.category = entity.getCategory();
    }
}
