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
    private Boolean userDelYn;

    public UserDto(User entity){

        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.userPw = entity.getUserPw();
        this.userName = entity.getUserName();
        this.userJoinDate=entity.getUserJoinDate();
        this.userPoint = entity.getUserPoint();
        this.userRole=entity.getUserRole();
        this.userDelYn = entity.getUserDelYn();
    }

}
