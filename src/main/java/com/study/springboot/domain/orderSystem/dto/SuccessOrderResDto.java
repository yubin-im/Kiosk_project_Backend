package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessOrderResDto {
    private Integer userPoint;
    private Long orderListId;
}
