package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.datas.Message;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.dto.AddProductReqDto;
import com.study.springboot.domain.orderSystem.dto.AmountControlReqDto;
import com.study.springboot.domain.orderSystem.dto.AmountControlResDto;
import com.study.springboot.domain.orderSystem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderItemController {
    final private OrderItemService orderItemService;

    // 상품 개수 1개 추가 및 총 상품 가격 변경
    @PostMapping("/order/addAmount")
    public Message addAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        Message message = orderItemService.addAmount(orderListId, productId);
        return message;
    }

    // 상품 개수 1개 삭제 및 총 상품 가격 변경
    @PostMapping("/order/removeAmount")
    public Message removeAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        Message message = orderItemService.removeAmount(orderListId, productId);
        return message;
    }

    // 장바구니에 상품 추가
    @PostMapping("/order/addProduct")
    public Message addProduct(@RequestBody AddProductReqDto addProductReqDto) {
        Long productId = addProductReqDto.getProductId();
        Long orderListId = addProductReqDto.getOrderListId();

        Message message = orderItemService.addProduct(productId, orderListId);

        return message;
    }

    // 장바구니에 상품 삭제
    @PostMapping("/order/removeProduct")
    public Message removeProduct(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        Message message = orderItemService.removeProduct(orderListId, productId);

        return message;
    }

}
