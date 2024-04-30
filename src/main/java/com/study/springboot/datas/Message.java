package com.study.springboot.datas;


import com.study.springboot.enumeration.error.StatusCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message<T> {

    private StatusCode status;
    private String code;
    private String message;
    private T result;


    @Builder
    public Message(final StatusCode status, final String code, final String message, final T result) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @Builder
    public Message(final StatusCode status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
