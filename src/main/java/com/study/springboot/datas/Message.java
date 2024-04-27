package com.study.springboot.datas;


import com.study.springboot.enumeration.error.StatusCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Message {

    private StatusCode status;
    private String code;
    private String message;


    @Builder
    public Message(StatusCode status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static Message userNotFound(){
        return new Message(StatusCode.USER_NOT_FOUND,
                StatusCode.USER_NOT_FOUND.getValue(),
                "회원을 찾을 수 없습니다" );
    }

    public static Message userPwInvalid(){
        return new Message(StatusCode.USER_LOGIN_PW_INVALID, StatusCode.USER_LOGIN_PW_INVALID.getValue(), "비밀번호가 틀렸습니다");
    }

    public static Message userLoginSuccess(){
        return new Message(StatusCode.USER_LOGIN, StatusCode.USER_LOGIN.getValue(), "로그인 성공");
    }

    public static Message userRegisterUserIdExists(){
        return new Message(StatusCode.USER_REG_ID_EXISTS, StatusCode.USER_REG_ID_EXISTS.getValue(), "이미 존재하는 회원입니다");
    }

    public static Message userRegisterSuccess(){
        return new Message(StatusCode.USER_REG_SUCCESS, StatusCode.USER_REG_SUCCESS.getValue(), "회원가입 성공");
    }

    public static Message adminPwInvalid(){
        return new Message(StatusCode.ADMIN_PW_INVALID, StatusCode.ADMIN_PW_INVALID.getValue(), "비밀번호가 틀렸습니다");
    }

    public static Message adminLoginSuccess(){
        return new Message(StatusCode.ADMIN_LOGIN, StatusCode.ADMIN_LOGIN.getValue(), "관리자 로그인 성공");
    }
}
