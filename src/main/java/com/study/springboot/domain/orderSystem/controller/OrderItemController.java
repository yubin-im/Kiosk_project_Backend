package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;
import com.study.springboot.domain.orderSystem.dto.AddProductReqDto;
import com.study.springboot.domain.orderSystem.dto.AmountControlReqDto;
import com.study.springboot.domain.orderSystem.dto.AmountControlResDto;
import com.study.springboot.domain.orderSystem.dto.OrderListDto;
import com.study.springboot.domain.orderSystem.service.OrderItemService;
import com.study.springboot.domain.orderSystem.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderItemController {
    final private OrderItemService orderItemService;
    private final MessageService messageService;
    private final OrderListService orderListService;

    // 상품 개수 1개 추가 및 총 상품 가격 변경
    @PostMapping("/order/addAmount")
    public ResponseEntity<Message> addAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        Optional<AmountControlResDto> optional = orderItemService.addAmount(orderListId, productId);
        if(optional.isPresent()){
            Message message = messageService.updateOrderAmountSuccessMessage(optional.get());
            return ResponseEntity.ok(message);
        }
        Message message = messageService.updateOrderAmountFailedMessage();

        return ResponseEntity.ok(message);
    }


    // 상품 개수 1개 삭제 및 총 상품 가격 변경
    @PostMapping("/order/removeAmount")
    public ResponseEntity<Message> removeAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Long orderListId = amountControlReqDto.getOrderListId();
        Long productId = amountControlReqDto.getProductId();

        Optional<AmountControlResDto> optional = orderItemService.removeAmount(orderListId, productId);
        if(optional.isPresent()){
            Message message = messageService.updateOrderAmountSuccessMessage(optional.get());
            return ResponseEntity.ok(message);
        }

        Message message = messageService.updateOrderAmountFailedMessage();
        return ResponseEntity.ok(message);
    }

    // 장바구니에 상품 추가
    @PostMapping("/order/addProduct")
    public ResponseEntity addProduct(@RequestBody AddProductReqDto addProductReqDto) {
        Long productId = addProductReqDto.getProductId();
        Long orderListId = addProductReqDto.getOrderListId();

        Optional<OrderListDto> optional = orderListService.addProduct(orderListId, productId);

        if(optional.isPresent()){
            Message message = messageService.addOrderListSuccessMessage(optional.get());
            return ResponseEntity.ok(message);
        }

        Message message = messageService.addOrderListFailedMessage();
        return ResponseEntity.ok(message);
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
