package com.study.springboot.domain.user.dto;


import com.study.springboot.domain.user.User;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.enumeration.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestEditUserDto {

    private Long id;

    private String userId;

    private String userName;

    private String userPw;

    private LocalDate userJoinDate;

    private Integer userPoint;

    private UserRole userRole;

    private List<OrderList> orderList;

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .userId(this.userId)
                .userId(this.userId)
                .userPw(this.userPw)
                .userRole(this.userRole)
                .userName(this.userName)
                .userJoinDate(this.userJoinDate)
                .orderList(this.orderList)
                .build();
    }

}
