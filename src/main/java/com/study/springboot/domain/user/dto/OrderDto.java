package com.study.springboot.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private ProductDto product;
    private Integer productAmount;
    private Integer totalPrice;
}
