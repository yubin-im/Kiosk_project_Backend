package com.study.springboot.datas;


import com.study.springboot.enumeration.error.StatusCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMessage extends Message {

    public AdminMessage(StatusCode status, String code, String message) {
        super(status, code, message);
    }
    public AdminMessage(StatusCode status, String code, String message, Object result) {
        super(status, code, message, result);
    }
}
