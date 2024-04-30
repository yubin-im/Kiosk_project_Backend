package com.study.springboot.datas;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String token;
    private String userId;

    @Builder
    public UserToken(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public static UserToken makeUserToken(String token, String userId){
        return UserToken.builder()
                .token(token)
                .userId(userId)
                .build();
    }
}
