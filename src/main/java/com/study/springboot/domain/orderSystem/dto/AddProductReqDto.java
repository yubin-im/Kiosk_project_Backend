package com.study.springboot.domain.orderSystem.dto;

import lombok.Getter;

@Getter
public class AddProductReqDto {
    private Long productId;
    private Long orderListId;
}
