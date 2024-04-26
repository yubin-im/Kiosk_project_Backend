package com.study.springboot.datas;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Message {

    private String status;
    private String message;

    @Builder
    public Message(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
