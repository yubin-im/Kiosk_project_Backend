package com.study.springboot.domain.member.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String memberRole;

    UserRole(String memberRole){
        this.memberRole = memberRole;
    }
}
