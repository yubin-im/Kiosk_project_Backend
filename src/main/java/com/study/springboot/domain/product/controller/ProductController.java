package com.study.springboot.domain.product.controller;


import com.study.springboot.domain.product.dto.*;
import com.study.springboot.domain.product.service.ProductService;
import com.study.springboot.enumeration.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 제품 9개 랜덤 추천 기능 (함께 즐기면 더욱 좋습니다!)
    @PostMapping("/order/recommend")
    @ResponseBody
    public List<RecommendProductDto> recommendProduct() {
        List<RecommendProductDto> randomProducts = productService.recommendProduct();

        return randomProducts;
    }


    // 메인 화면- 카테고리 별 제품 전체 출력(페이징 9개씩), 사용자 이름 출력, 총가격 및 총수량 출력
//    @PostMapping("/order")
    @ResponseBody
    public ProductsResDto getProductsByCategory(@RequestBody ProductsReqDto productsReqDto) {
        ProductCategory category = ProductCategory.valueOf(productsReqDto.getCategory());
        Pageable pageable = PageRequest.of(productsReqDto.getPage(), 9);

        Long orderListId = productsReqDto.getOrderListId();

        ProductsResDto productsResDto = productService.getProductsByCategory(category, pageable, orderListId);
        return productsResDto;
    }

    // 메인 화면- 카테고리 별 제품 전체 출력(페이징 9개씩), 부가 기능없이 제품만 출력하는 메소드
    @PostMapping("/order")
    @ResponseBody
    public List<ProductsByCategoryDto> onlyGetProductsByCategory(@RequestBody OnlyGetProductsReq onlyGetProductsReq) {
        ProductCategory productCategory = ProductCategory.valueOf(onlyGetProductsReq.getCategory());
        Pageable pageable = PageRequest.of(onlyGetProductsReq.getPage(), 9);

        List<ProductsByCategoryDto> productsByCategoryDtos = productService.onlyGetProductsByCategory(productCategory, pageable);
        return productsByCategoryDtos;
    }

}
