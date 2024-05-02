package com.study.springboot.domain.user.dto;

import com.study.springboot.enumeration.UserRole;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserUpdateRequestDto {
    String userId;
    String userPw;
    String userName;
    UserRole userRole;
    LocalDate userJoinDate;
    Integer userPoint;
}
