package com.study.springboot.domain.product.dto;

import com.study.springboot.domain.category.entity.Category;
import com.study.springboot.domain.orderItem.entity.OrderItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String productName;
    private int productPrice;
    private String productImgUrl;
    private String productCode;
    private Category category;
    private List<OrderItem> orderItemList;
}
