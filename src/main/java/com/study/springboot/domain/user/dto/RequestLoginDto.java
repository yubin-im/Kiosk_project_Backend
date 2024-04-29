package com.study.springboot.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginDto {
    private String userId;
    private String userPw;
}
