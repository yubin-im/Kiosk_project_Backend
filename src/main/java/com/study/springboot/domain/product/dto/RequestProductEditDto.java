package com.study.springboot.domain.product.dto;


import com.study.springboot.domain.product.Product;
import com.study.springboot.enumeration.ProductCategory;
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

    private ProductCategory category;

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
