package com.study.springboot.domain.user.dto;

import com.study.springboot.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestAddUserDto {
    private String userId;
    private String userPw;
    private String userName;

    public User toUserEntity(){
        return User.makeUser(this.userId, this.userPw, this.userName);
    }
    public User toAdminEntity(){
        return User.makeAdminUser(this.userId, this.userPw, this.userName);
    }
}
