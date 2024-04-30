package com.study.springboot.datas;

import com.study.springboot.enumeration.error.StatusCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMessage extends Message {


    public AdminMessage(StatusCode status, String code, String message) {
        super(status, code, message);
    }

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
                "주문 아이템을 수정 성공");
    }

    //주문 아이템, 주문 리스트 불일치
    public static Message orderItemOrderListMisMatchMessage(){
        return new AdminMessage(
                StatusCode.ORDER_ITEM_ORDER_LIST_MISMATCH,
                StatusCode.ORDER_ITEM_ORDER_LIST_MISMATCH.getValue(),
                "주문 아이템과 주문 리스트가 일치하지 않습니다.");
    }

}
