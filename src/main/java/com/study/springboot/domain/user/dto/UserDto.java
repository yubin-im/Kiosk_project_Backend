package com.study.springboot.domain.user.dto;

import com.study.springboot.domain.user.User;
import com.study.springboot.enumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private LocalDate userJoinDate;
    private Integer userPoint;
    private UserRole userRole;

    public UserDto(User entity){

        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.userPw = entity.getUserPw();
        this.userName = entity.getUserName();
        this.userJoinDate=entity.getUserJoinDate();
        this.userPoint = entity.getUserPoint();
        this.userRole=entity.getUserRole();
    }


//     public User toEntity(){
//         return User.builder()
//                 .id(this.id)
//                 .userId(this.userId)
//                 .userId(this.userId)
//                 .userPw(this.userPw)
//                 .userRole(this.userRole)
//                 .userName(this.userName)
//                 .userJoinDate(this.userJoinDate)
//                 .orderList(this.orderList)
//                 .build();
//     }
}
