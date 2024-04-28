package com.study.springboot.domain.product.dto;


import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.SearchCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSearchDto {
    private ProductCategory searchProductCategory;
    private String searchKeyword;
    private Integer pageSize;
    private Integer page;
}
