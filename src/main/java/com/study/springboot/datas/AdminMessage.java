package com.study.springboot.datas;

import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.OrderCountListDto;
import com.study.springboot.domain.orderSystem.dto.OrderListDto;
import com.study.springboot.domain.orderSystem.dto.OrderRevenueListDto;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.dto.UserListDto;
import com.study.springboot.enumeration.error.StatusCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMessage extends Message {


    public AdminMessage(StatusCode status, String code, String message) {
        super(status, code, message);
    }


    public AdminMessage(StatusCode status, String code, String message, Object result) {
        super(status, code, message, result);
    }

    /*
    User
     */

    //회원 존재하지 않음
    public static Message userNotFoundMessage(){
        return new AdminMessage(StatusCode.USER_NOT_FOUND,
                StatusCode.USER_NOT_FOUND.getValue(),
                "회원을 찾을 수 없습니다.");
    }

    //회원 목록 없음
    public static Message userListNotFoundMessage(){
        return new AdminMessage(StatusCode.USER_LIST_NOT_FOUND,
                StatusCode.USER_LIST_NOT_FOUND.getValue(),
                "회원 목록 없음");
    }

    //회원 목록 조회 성공
    public static Message userListFoundSuccessMessage(Page<UserDto> dto){
        return new AdminMessage(StatusCode.USER_LIST_FOUND_SUCCESS,
                StatusCode.USER_LIST_FOUND_SUCCESS.getValue(),
                "회원 목록 조회 성공",
                dto);
    }

    //회원 상세 조회 성공
    public static Message userFoundSuccessMessage(UserDto dto){
        return new AdminMessage(StatusCode.USER_FOUND_SUCCESS,
                StatusCode.USER_FOUND_SUCCESS.getValue(),
                "회원 조회 성공",
                dto);
    }

    //회원 삭제 성공
    public static Message userDeleteSuccessMessage(){
        return new AdminMessage(StatusCode.USER_DELETE_SUCCESS,
                StatusCode.USER_DELETE_SUCCESS.getValue(),
                "회원 삭제 성공");
    }

    //회원 수정 성공
    public static Message userUpdateSuccessMessage(){
        return new AdminMessage(StatusCode.USER_UPDATE_SUCCESS,
                StatusCode.USER_UPDATE_SUCCESS.getValue(),
                "회원 수정 성공");
    }


    /*
    Product
     */

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


    /*
    OrderList
     */

    //주문 목록 없음
    public static Message orderListNotFoundMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_NOT_FOUND,
                StatusCode.ORDER_LIST_NOT_FOUND.getValue(),
                "주문 목록 없음");
    }

    //주문 목록 조회 성공
    public static Message orderListFoundSuccessMessage(List<OrderListDto> dto){
        return new AdminMessage(StatusCode.ORDER_LIST_FOUND_SUCCESS,
                StatusCode.ORDER_LIST_FOUND_SUCCESS.getValue(),
                "주문 목록 조회 성공",
                dto);
    }

    //주문 상세 조회 성공
    public static Message orderFoundSuccessMessage(OrderList dto){
        return new AdminMessage(StatusCode.ORDER_FOUND_SUCCESS,
                StatusCode.ORDER_FOUND_SUCCESS.getValue(),
                "주문 상세 조회 성공",
                dto);
    }

    //주문 삭제 성공
    public static Message orderListDeleteSuccessMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_DELETE_SUCCESS,
                StatusCode.ORDER_LIST_DELETE_SUCCESS.getValue(),
                "주문 삭제 성공");
    }

    //주문 수정 성공
    public static Message orderListUpdateSuccessMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_UPDATE_SUCCESS,
                StatusCode.ORDER_LIST_UPDATE_SUCCESS.getValue(),
                "주문 수정 성공");
    }

    //주문 상세 아이템 존재하지 않음
    public static Message orderItemNotFoundMessage(){
        return new AdminMessage(
                StatusCode.ORDER_ITEM_NOT_FOUND,
                StatusCode.ORDER_ITEM_NOT_FOUND.getValue(),
                "주문 아이템을 찾을 수 없습니다");
    }

    //주문 아이템 수정 완료
    public static Message orderItemUpdateSuccessMessage(){
        return new AdminMessage(
                StatusCode.ORDER_ITEM_UPDATE_SUCCESS,
                StatusCode.ORDER_ITEM_UPDATE_SUCCESS.getValue(),
                "주문 아이템 수정 성공");
    }

    //주문 아이템, 주문 리스트 불일치
    public static Message orderItemOrderListMisMatchMessage(){
        return new AdminMessage(
                StatusCode.ORDER_ITEM_ORDER_LIST_MISMATCH,
                StatusCode.ORDER_ITEM_ORDER_LIST_MISMATCH.getValue(),
                "주문 아이템과 주문 리스트가 일치하지 않습니다.");
    }

    //주문 통계 조회 실패
    public static Message orderStatisticsListNotFoundMessage(){
        return new AdminMessage(StatusCode.ORDER_LIST_NOT_FOUND,
                StatusCode.ORDER_LIST_NOT_FOUND.getValue(),
                "주문 통계 목록 없음");
    }

    //주문 수입 통계 조회 성공
    public static Message orderRevenueListFoundSuccessMessage(OrderRevenueListDto dto){
        return new AdminMessage(StatusCode.ORDER_REVENUE_LIST_FOUND_SUCCESS,
                StatusCode.ORDER_REVENUE_LIST_FOUND_SUCCESS.getValue(),
                "주문 수입 목록 조회 성공",
                dto);
    }

    //주문 횟수 통계 조회 성공
    public static Message orderCountListFoundSuccessMessage(OrderCountListDto dto){
        return new AdminMessage(StatusCode.ORDER_COUNT_LIST_FOUND_SUCCESS,
                StatusCode.ORDER_COUNT_LIST_FOUND_SUCCESS.getValue(),
                "주문 횟수 목록 조회 성공",
                dto);
    }
}
