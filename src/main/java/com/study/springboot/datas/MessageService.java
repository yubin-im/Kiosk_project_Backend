package com.study.springboot.datas;


import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.*;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.ProductsResDto;
import com.study.springboot.domain.product.dto.RecommendProductDto;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.dto.UserListDto;
import com.study.springboot.enumeration.error.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    //상품 삭제 실패
    public static Message productRemoveSuccessMessage(){
        return new AdminMessage(StatusCode.PRODUCT_REMOVE_SUCCESS,
                StatusCode.PRODUCT_REMOVE_SUCCESS.getValue(),
                "상품 삭제 성공");
    }


    //상품코드 존재하지 않음
    public Message productNotFoundMessage(){
        return new AdminMessage(
                StatusCode.PRODUCT_NOT_FOUND,
                StatusCode.PRODUCT_NOT_FOUND.getValue(),
                "상품을 찾을 수 없습니다");
    }

    //상품코드 존재하지 않음
    public Message productListNotFoundMessage(){
        return new AdminMessage(
                StatusCode.PRODUCT_LIST_NOT_FOUND,
                StatusCode.PRODUCT_LIST_NOT_FOUND.getValue(),
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


    public Message userListNotFoundMessage(){
        return new AdminMessage(StatusCode.USER_LIST_NOT_FOUND,
                StatusCode.USER_LIST_NOT_FOUND.getValue(),
                "회원 목록 없음");
    }

    public static Message userListFoundSuccessMessage(Page<UserDto> dto){
        return new AdminMessage(StatusCode.USER_LIST_FOUND_SUCCESS,
                StatusCode.USER_LIST_FOUND_SUCCESS.getValue(),
                "회원 목록 조회 성공",
                dto);
    }


    public Message userFoundSuccessMessage(UserDto dto){
        return new AdminMessage(StatusCode.USER_FOUND_SUCCESS,
                StatusCode.USER_FOUND_SUCCESS.getValue(),
                "회원 조회 성공",
                dto);
    }

    //회원 삭제 성공
    public Message userDeleteSuccessMessage(){
        return new AdminMessage(StatusCode.USER_DELETE_SUCCESS,
                StatusCode.USER_DELETE_SUCCESS.getValue(),
                "회원 삭제 성공");
    }

    //회원 존재하지 않음
    public Message userNotFoundMessage(){
        return new AdminMessage(StatusCode.USER_NOT_FOUND,
                StatusCode.USER_NOT_FOUND.getValue(),
                "회원을 찾을 수 없습니다.");
    }

    //회원 수정 성공
    public Message userUpdateSuccessMessage(){
        return new AdminMessage(StatusCode.USER_UPDATE_SUCCESS,
                StatusCode.USER_UPDATE_SUCCESS.getValue(),
                "회원 수정 성공");
    }

    /*
    OrderList
     */

    //주문 목록 없음
    public Message orderListNotFoundMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_NOT_FOUND,
                StatusCode.ORDER_LIST_NOT_FOUND.getValue(),
                "주문 목록 없음");
    }

    //주문 상세 조회 성공
    public  Message orderFoundSuccessMessage(OrderListDetailDto dto){
        return new AdminMessage(StatusCode.ORDER_FOUND_SUCCESS,
                StatusCode.ORDER_FOUND_SUCCESS.getValue(),
                "주문 상세 조회 성공",
                dto);
    }

    //주문 목록 조회 성공
    public  Message orderListFoundSuccessMessage(List<OrderListDto> dto){
        return new AdminMessage(StatusCode.ORDER_LIST_FOUND_SUCCESS,
                StatusCode.ORDER_LIST_FOUND_SUCCESS.getValue(),
                "주문 목록 조회 성공",
                dto);
    }

    //주문 삭제 성공
    public Message orderListDeleteSuccessMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_DELETE_SUCCESS,
                StatusCode.ORDER_LIST_DELETE_SUCCESS.getValue(),
                "주문 삭제 성공");
    }

    //주문 수정 성공
    public Message orderListUpdateSuccessMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_UPDATE_SUCCESS,
                StatusCode.ORDER_LIST_UPDATE_SUCCESS.getValue(),
                "주문 수정 성공");
    }


    //주문 아이템 수정 완료
    public Message orderItemUpdateSuccessMessage(){
        return new AdminMessage(
                StatusCode.ORDER_ITEM_UPDATE_SUCCESS,
                StatusCode.ORDER_ITEM_UPDATE_SUCCESS.getValue(),
                "주문 아이템 수정 성공");
    }


    //주문 상세 아이템 존재하지 않음
    public Message orderItemNotFoundMessage(){
        return new AdminMessage(
                StatusCode.ORDER_ITEM_NOT_FOUND,
                StatusCode.ORDER_ITEM_NOT_FOUND.getValue(),
                "주문 아이템을 찾을 수 없습니다");
    }


    //주문 수입 통계 조회 성공
    public Message orderRevenueListFoundSuccessMessage(OrderRevenueListDto dto){
        return new AdminMessage(StatusCode.ORDER_REVENUE_LIST_FOUND_SUCCESS,
                StatusCode.ORDER_REVENUE_LIST_FOUND_SUCCESS.getValue(),
                "주문 수입 목록 조회 성공",
                dto);
    }

    //주문 통계 조회 실패
    public Message orderStatisticsListNotFoundMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_NOT_FOUND,
                StatusCode.ORDER_LIST_NOT_FOUND.getValue(),
                "주문 통계 목록 없음");
    }

    //주문 횟수 통계 조회 성공
    public Message orderCountListFoundSuccessMessage(OrderCountListDto dto){
        return new AdminMessage(StatusCode.ORDER_COUNT_LIST_FOUND_SUCCESS,
                StatusCode.ORDER_COUNT_LIST_FOUND_SUCCESS.getValue(),
                "주문 횟수 목록 조회 성공",
                dto);
    }

    public Message productFoundSuccessMessage(){
        return new AdminMessage(StatusCode.PRODUCT_CHECK_SUCCESS, StatusCode.PRODUCT_CHECK_SUCCESS.getValue(), "상품 조회 성공");
    }

    public Message productListFoundSuccessMessage (List<ProductDto> dtoList){
        return new AdminMessage(StatusCode.PRODUCT_CHECK_SUCCESS, StatusCode.PRODUCT_CHECK_SUCCESS.getValue(), "상품 조회 성공", dtoList);
    }


    public Message recommendationNotFound(){
        return Message.builder()
                .status(StatusCode.PRODUCT_NOT_FOUND)
                .code(StatusCode.PRODUCT_NOT_FOUND.getValue())
                .message("추천 상품 조회 실패")
                .build();
    }

    public Message recommendationSuccessMessage(List<RecommendProductDto> dto){
        return Message.builder()
                .status(StatusCode.PRODUCT_CHECK_SUCCESS)
                .code(StatusCode.PRODUCT_CHECK_SUCCESS.getValue())
                .message("추천 상품 조회 성공!")
                .result(dto)
                .build();
    }

    public Message productsByCategoryFoundSuccessMessage(ProductsResDto dto){
        return Message.builder()
                .status(StatusCode.PRODUCT_CHECK_SUCCESS)
                .code(StatusCode.PRODUCT_CHECK_SUCCESS.getValue())
                .message("상품 조회가 완료되었습니다!")
                .result(dto)
                .build();
    }

    public Message addOrderListSuccessMessage(OrderListDto orderList){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_CREATE_SUCCESS)
                .code(StatusCode.ORDER_LIST_CREATE_SUCCESS.getValue())
                .message("장바구니가 생성되었습니다!")
                .result(orderList)
                .build();
    }

    public Message addOrderListFailedMessage(){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_CREATE_FAILED)
                .code(StatusCode.ORDER_LIST_CREATE_FAILED.getValue())
                .message("상품 추가 실패")
                .build();
    }

    public Message paymentSuccessMessage(PaymentResDto paymentResDto){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_PAYMENT_SUCCESS)
                .code(StatusCode.ORDER_LIST_PAYMENT_SUCCESS.getValue())
                .message("결제가 정상적으로 진행되었습니다!")
                .result(paymentResDto)
                .build();
    }
    public Message paymentSuccessMessage2(Long id){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_PAYMENT_SUCCESS)
                .code(StatusCode.ORDER_LIST_PAYMENT_SUCCESS.getValue())
                .message("결제가 정상적으로 진행되었습니다!")
                .result(id)
                .build();
    }

    public Message paymentFailedMessage(){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_PAYMENT_FAILED)
                .code(StatusCode.ORDER_LIST_PAYMENT_FAILED.getValue())
                .message("결제 실패")
                .build();
    }

    public Message orderSuccessMessage(SuccessOrderResDto dto){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_SUCCESS)
                .code(StatusCode.ORDER_LIST_SUCCESS.getValue())
                .message("주문이 정상적으로 완료되었습니다!")
                .result(dto)
                .build();
    }

    public Message orderFailedMessage(){
        return Message.builder()
                .status(StatusCode.ORDER_LIST_CREATE_FAILED)
                .code(StatusCode.ORDER_LIST_CREATE_FAILED.getValue())
                .message("주문 실패")
                .build();
    }

    public Message orderDetailSuccessMessage(OrderDetailResDto dto){
        return Message.builder()
                .status(StatusCode.ORDER_FOUND_SUCCESS)
                .code(StatusCode.ORDER_FOUND_SUCCESS.getValue())
                .message("상품 상세 조회 성공")
                .result(dto)
                .build();

    }

    public Message updateOrderAmountSuccessMessage(AmountControlResDto dto){
        return Message.builder()
                .status(StatusCode.PRODUCT_EDIT_SUCCESS)
                .code(StatusCode.PRODUCT_EDIT_SUCCESS.getValue())
                .message("상품 수량이 변경되었습니다!")
                .result(dto)
                .build();

    }

    public Message updateOrderAmountFailedMessage(){
        return Message.builder()
                .status(StatusCode.PRODUCT_EDIT_FAILED)
                .code(StatusCode.PRODUCT_EDIT_FAILED.getValue())
                .message("상품 수량 변경 실패")
                .build();

    }




}
