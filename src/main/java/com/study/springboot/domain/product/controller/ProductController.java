package com.study.springboot.domain.product.controller;


import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;
import com.study.springboot.domain.product.dto.*;
import com.study.springboot.domain.product.service.ProductService;
import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.error.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MessageService messageService;

    // 제품 9개 랜덤 추천 기능 (함께 즐기면 더욱 좋습니다!)
    @PostMapping("/order/recommend")
    @ResponseBody
    public ResponseEntity recommendProduct() {
        List<RecommendProductDto> dto = productService.recommendProduct();
        if(dto.isEmpty()){
            Message message = messageService.recommendationNotFound();
            return ResponseEntity.ok(message);
        }

        Message message = messageService.recommendationSuccessMessage(dto);
        return ResponseEntity.ok(message);
    }


    // 메인 화면- 카테고리 별 제품 전체 출력(페이징 9개씩), 사용자 이름 출력, 총가격 및 총수량 출력
//    @PostMapping("/order")
//    @ResponseBody
//    public ResponseEntity getProductsByCategory(@RequestBody ProductsReqDto productsReqDto) {
//        ProductCategory category = ProductCategory.valueOf(productsReqDto.getCategory());
//        Pageable pageable = PageRequest.of(productsReqDto.getPage(), 9);
//
//        Long orderListId = productsReqDto.getOrderListId();
//        Optional<ProductsResDto> optional = productService.getProductsByCategory(category, pageable, orderListId);
//
//        Message message;
//        if(optional.isPresent()){
//            message = messageService.productsByCategoryFoundSuccessMessage(optional.get());
//            return ResponseEntity.ok(message);
//        }
//
//        message = messageService.productListNotFoundMessage();
//        return ResponseEntity.ok(message);
//    }


    // 메인 화면- 카테고리 별 제품 전체 출력(페이징 9개씩), 부가 기능없이 제품만 출력하는 메소드
    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity onlyGetProductsByCategory(@RequestBody OnlyGetProductsReq onlyGetProductsReq) {
        ProductCategory productCategory = ProductCategory.valueOf(onlyGetProductsReq.getCategory());
        Pageable pageable = PageRequest.of(onlyGetProductsReq.getPage(), 9);

        System.out.println("onlyGetProductsReqCa = " + onlyGetProductsReq.getCategory());
        System.out.println("onlyGetProductsReq.getPage() = " + onlyGetProductsReq.getPage());
        List<ProductsByCategoryDto> list = productService.onlyGetProductsByCategory(productCategory, pageable);

        if(list.isEmpty()){
            Message message = messageService.productNotFoundMessage();
            return ResponseEntity.ok(message);
        }

        Message message = Message.builder()
                .status(StatusCode.PRODUCT_CHECK_SUCCESS)
                .code(StatusCode.PRODUCT_CHECK_SUCCESS.getValue())
                .message("상품 조회가 완료되었습니다!")
                .result(list)
                .build();

        return ResponseEntity.ok(message);
    }

}
