package com.study.springboot.enumeration;

public enum UserRole {
    USER("ROLE_ADMIN"), ADMIN("ROLE_ADMIN");

    private String value;

    UserRole(String value){
        this.value = value;
    }
}
