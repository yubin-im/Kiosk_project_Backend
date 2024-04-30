package com.study.springboot.datas;


import com.study.springboot.enumeration.error.StatusCode;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public Message userNotFound(){
        return new Message(StatusCode.USER_NOT_FOUND,
                StatusCode.USER_NOT_FOUND.getValue(),
                "회원을 찾을 수 없습니다" );
    }

    public Message userPwInvalid(){
        return new Message(StatusCode.USER_LOGIN_PW_INVALID, StatusCode.USER_LOGIN_PW_INVALID.getValue(), "비밀번호가 틀렸습니다");
    }

    public Message userLoginSuccess(UserToken userToken){
        return new Message(StatusCode.USER_LOGIN, StatusCode.USER_LOGIN.getValue(), "로그인 성공", userToken);
    }

    public Message userRegisterUserIdExists(){
        return new Message(StatusCode.USER_REG_ID_EXISTS, StatusCode.USER_REG_ID_EXISTS.getValue(), "이미 존재하는 회원입니다");
    }

    public Message userRegisterSuccess(){
        return new Message(StatusCode.USER_REG_SUCCESS, StatusCode.USER_REG_SUCCESS.getValue(), "회원가입 성공");
    }

    public Message adminPwInvalid(){
        return new Message(StatusCode.ADMIN_PW_INVALID, StatusCode.ADMIN_PW_INVALID.getValue(), "비밀번호가 틀렸습니다");
    }

    public Message adminLoginSuccess(){
        return new Message(StatusCode.ADMIN_LOGIN, StatusCode.ADMIN_LOGIN.getValue(), "관리자 로그인 성공");
    }

    public Message adminNoPermission(){
        return new Message(StatusCode.ADMIN_NO_PERMISSION, StatusCode.ADMIN_NO_PERMISSION.getValue(), "권한이 없습니다");
    }

    public Message userNoPermission(){
        return new Message(StatusCode.USER_NO_PERMISSION, StatusCode.USER_NO_PERMISSION.getValue(), "권한이 없습니다");
    }



}
