package com.study.springboot.domain.orderSystem.controller;

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
    public AmountControlResDto addAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        AmountControlResDto amountControlResDto = orderItemService.addAmount(orderListId, productId);
        return amountControlResDto;
    }

    // 상품 개수 1개 삭제 및 총 상품 가격 변경
    @PostMapping("/order/removeAmount")
    public AmountControlResDto removeAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        AmountControlResDto amountControlResDto = orderItemService.removeAmount(orderListId, productId);
        return amountControlResDto;
    }

    // 장바구니에 상품 추가
    @PostMapping("/order/addProduct")
    public OrderItem addProduct(@RequestBody AddProductReqDto addProductReqDto) {
        Long productId = addProductReqDto.getProductId();
        Long orderListId = addProductReqDto.getOrderListId();

        OrderItem orderItem = orderItemService.addProduct(productId, orderListId);

        return orderItem;
    }

    // 장바구니에 상품 삭제
    @PostMapping("/order/removeProduct")
    public String removeProduct(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        String message = orderItemService.removeProduct(orderListId, productId);

        return message;
    }

}
