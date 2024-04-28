package com.study.springboot.domain.admin;


import com.study.springboot.datas.KioskSession;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.member.service.UserService;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.RequestProductEditDto;
import com.study.springboot.domain.product.dto.RequestProductRemoveDto;
import com.study.springboot.domain.product.dto.RequestSearchDto;
import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.SearchCategory;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    /*
    product service
     */
    @GetMapping("/product/list")
    public ResponseEntity productList(HttpSession session){

        //관리자가 아니라면 에러 코드
        if(!KioskSession.isAdmin(session)){
            Message message = Message.userNoPermission();
            return ResponseEntity.ok(message);
        }

        List<ProductDto> dto = adminService.findAllProduct();

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/product/remove")
    public ResponseEntity productRemove(@RequestBody RequestProductRemoveDto dto, HttpSession session){

        //관리자가 아니라면 에러 코드
        if(!KioskSession.isAdmin(session)){
            Message message = Message.userNoPermission();
            return ResponseEntity.ok(message);
        }

        String code = dto.getProductCode();
        String productName = dto.getProductName();

        Message message = adminService.setRemoveProductMessage(code, productName);

        return ResponseEntity.ok(message);

    }

    @GetMapping("/product/detail")
    public ResponseEntity productDetail(@RequestParam(name = "code") String code, HttpSession session){
        //관리자가 아니라면 에러 코드
        if(!KioskSession.isAdmin(session)){
            Message message = Message.userNoPermission();
            return ResponseEntity.ok(message);
        }

        Optional<Product> optional = adminService.findProductByCode(code);
        ProductDto dto = new ProductDto(optional.get());

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/product/detail/edit")
    public ResponseEntity productEdit(@RequestBody RequestProductEditDto dto, HttpSession session){
        //관리자가 아니라면 에러 코드
//        if(!KioskSession.isAdmin(session)){
//            Message message = Message.userNoPermission();
//            return ResponseEntity.ok(message);
//        }

        Message message = adminService.editProduct(dto);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/product/list/search")
    public ResponseEntity productSearch(@RequestBody RequestSearchDto dto, HttpSession session){
        String searchKeyword = dto.getSearchKeyword();
        Integer page = dto.getPage();
        Integer pageSize = dto.getPageSize();
        ProductCategory category = dto.getSearchProductCategory();


        List<Product> result = adminService.findProductsBy(category, searchKeyword, page, pageSize);
        return ResponseEntity.ok(result);

    }

}
