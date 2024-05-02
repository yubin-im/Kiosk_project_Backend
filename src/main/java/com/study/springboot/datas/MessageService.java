package com.study.springboot.datas;


import com.study.springboot.enumeration.error.StatusCode;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    //상품 삭제 실패
    public static Message productRemoveSuccessMessage(){
        return new AdminMessage(StatusCode.PRODUCT_REMOVE_SUCCESS,
                StatusCode.PRODUCT_REMOVE_SUCCESS.getValue(),
                "상품 삭제 성공");
    }


    //상품코드 존재하지 않음
    public static Message productNotFoundMessage(){
        return new AdminMessage(
                StatusCode.PRODUCT_NOT_FOUND,
                StatusCode.PRODUCT_NOT_FOUND.getValue(),
                "상품을 찾을 수 없습니다");
    }

    //상품명과 상품코드 불일치
    public static Message productCodeMisMatchMessage(){
        return new AdminMessage(StatusCode.PRODUCT_CODE_MISMATCH,
                StatusCode.PRODUCT_CODE_MISMATCH.getValue(),
                "상품명과 상품코드가 불일치합니다");
    }

    public static Message productEditSuccess(){
        return new AdminMessage(StatusCode.PRODUCT_EDIT_SUCCESS, StatusCode.PRODUCT_EDIT_SUCCESS.getValue(), "상품 수정 성공");
    }

    public static Message productFetchSuccess(){
        return new AdminMessage(StatusCode.PRODUCT_CHECK_SUCCESS, StatusCode.PRODUCT_CHECK_SUCCESS.getValue(), "성공");
    }

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

    public Message userRegisterFailed(){
        return new Message(StatusCode.USER_REG_FAILED, StatusCode.USER_REG_FAILED.getValue(), "회원가입 실패");
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
