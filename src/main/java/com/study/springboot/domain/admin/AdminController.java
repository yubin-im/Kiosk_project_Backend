package com.study.springboot.domain.admin;

import com.study.springboot.datas.KioskSession;
import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;

import com.study.springboot.domain.orderSystem.dto.*;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.dto.UserListDto;
import com.study.springboot.domain.user.service.UserService;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.service.OrderListService;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.RequestProductEditDto;
import com.study.springboot.domain.product.dto.RequestProductRemoveDto;
import com.study.springboot.domain.product.dto.RequestSearchDto;
import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.SearchCategory;
import com.study.springboot.enumeration.UserRole;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {
    private final UserService userService;
    private final OrderListService orderListService;
    private final AdminService adminService;
    private final MessageService messageService;


    /*
    User
     */

    //회원 목록
    @GetMapping("/user")
    public ResponseEntity<UserListDto> userList(@RequestParam(value="type", required = false) @Nullable String type,
                                                @RequestParam(value="text", required = false) @Nullable String text,
                                                @RequestParam(value="page", required = false, defaultValue = "0") @Nullable int page
                                                  ){
        return ResponseEntity.ok().body(adminService.getUserList(type, text, page));
    }

    //회원 상세 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(adminService.getUser(id));
    }

    //회원 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(adminService.deleteUser(id));
    }

    //회원 수정
    @PutMapping("/user/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto){
        return ResponseEntity.ok().body(adminService.updateUser(id, dto));
    }



    /*
    OrderList
     */

    //주문 목록 조회
    @GetMapping("/order")
    public ResponseEntity<List<OrderListDto>> orderList(@RequestParam(value="type", required = false) @Nullable String type,
                                                        @RequestParam(value="text", required = false) @Nullable String text,
                                                        @RequestParam(value="page", required = false, defaultValue = "0") @Nullable int page
    ){
        return ResponseEntity.ok().body(adminService.getOrderList(type, text, page));
    }

    //주문 상세 조회
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderList> getOrderList(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(adminService.getOrder(id));
    }

    //주문 삭제
    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOrderList(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(adminService.deleteOrderList(id));
    }

    //주문 수정
    @PutMapping("/order/{id}")
    public ResponseEntity updateOrderList(@PathVariable("id") Long id, @RequestBody OrderListUpdateDto dto){
        return ResponseEntity.ok().body(adminService.updateOrderList(id, dto));
    }

    //주문별 상세 아이템 수정
    @PutMapping("/order/{orderListId}/item")
    public ResponseEntity updateOrderItem(@PathVariable("orderListId") Long orderListId,@RequestBody OrderItemUpdateRequestDto dto){
        return ResponseEntity.ok().body(adminService.updateOrderItem(orderListId, dto));
    }

    //주문 통계 - 날짜별 주문 금액 조회
    @GetMapping("/order/statistics/revenue")
    public ResponseEntity<OrderRevenueListDto> getOrderRevenue(@RequestParam(value="type", required = false, defaultValue = "month") @Nullable String type,
                                                               @RequestParam(value="year", required = false, defaultValue = "2024") @Nullable int year,
                                                               @RequestParam(value="month", required = false, defaultValue = "4") @Nullable int month){
        return ResponseEntity.ok(adminService.getOrderRevenue(type, year, month));
    }

    //주문 통계 - 날짜별 주문 수 조회
    @GetMapping("/order/statistics/orders")
    public ResponseEntity<OrderCountListDto> getOrderCount(@RequestParam(value="type", required = false, defaultValue = "month") @Nullable String type,
                                                           @RequestParam(value="year", required = false, defaultValue = "2024") @Nullable int year,
                                                           @RequestParam(value="month", required = false, defaultValue = "4") @Nullable int month){
        return ResponseEntity.ok(adminService.getOrderCount(type, year, month));
    }


    /*
    product service
     */
    @GetMapping("/product/list")
    public ResponseEntity productList(HttpSession session){

        //관리자가 아니라면 에러 코드
        if(!KioskSession.isAdmin(session)){
            Message message = messageService.userNoPermission();
            return ResponseEntity.ok(message);
        }

        List<ProductDto> dto = adminService.findAllProduct();

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/product/remove")
    public ResponseEntity productRemove(@RequestBody RequestProductRemoveDto dto, HttpSession session){

        //관리자가 아니라면 에러 코드
        if(!KioskSession.isAdmin(session)){
            Message message = messageService.userNoPermission();
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
            Message message = messageService.userNoPermission();
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
        ProductCategory productCategory = dto.getProductCategory();
        SearchCategory searchCategory = dto.getSearchCategory();


        List<Product> result = adminService.findProductsBy(searchCategory, productCategory, searchKeyword, page, pageSize);
        return ResponseEntity.ok(result);
    }


}
