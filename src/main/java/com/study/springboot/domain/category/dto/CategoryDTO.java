package com.study.springboot.domain.category.dto;

import com.study.springboot.domain.product.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private List<Product> productList;
}
