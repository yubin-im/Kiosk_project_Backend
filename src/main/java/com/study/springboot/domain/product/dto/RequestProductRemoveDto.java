package com.study.springboot.domain.product.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestProductRemoveDto {
    private String productCode;
}
