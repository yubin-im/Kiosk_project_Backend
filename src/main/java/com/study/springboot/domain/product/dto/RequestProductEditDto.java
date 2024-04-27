package com.study.springboot.domain.product.dto;


import com.study.springboot.domain.product.Product;
import com.study.springboot.enumeration.CategoryProduct;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestProductEditDto {
    private String productName;

    private Integer productPrice;

    private String productCode;

    private String productImgUrl;

    private CategoryProduct category;

    public Product toEntity(Long id){
        return Product.builder()
                .id(id)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productImgUrl(this.productImgUrl)
                .category(this.category)
                .productCode(this.productCode)
                .build();
    }


}
