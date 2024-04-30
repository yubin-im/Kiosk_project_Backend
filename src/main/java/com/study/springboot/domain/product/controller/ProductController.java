package com.study.springboot.domain.product.controller;


import com.study.springboot.domain.product.dto.RecommendProductDto;
import com.study.springboot.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
