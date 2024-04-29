package com.study.springboot.domain.admin;

import com.study.springboot.domain.member.dto.UserDto;
import com.study.springboot.domain.member.dto.UserListDto;
import com.study.springboot.domain.member.service.MemberService;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.OrderListDto;
import com.study.springboot.domain.orderSystem.dto.OrderListUpdateDto;
import com.study.springboot.domain.orderSystem.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberService memberService;
    private final OrderListService orderListService;

    /*
    회원 목록
     */
    @GetMapping("/member")
    public ResponseEntity<UserListDto> memberList(@RequestParam(value="type", required = false) @Nullable String type,
                                                  @RequestParam(value="text", required = false) @Nullable String text,
                                                  @RequestParam(value="page", required = false, defaultValue = "0") @Nullable int page
                                                  ){
        return ResponseEntity.ok().body(memberService.getMemberList(type, text, page));
    }

    /*
    회원 상세 조회
    TODO: JPA N+1 문제 해결하기
     */
    @GetMapping("/member/{id}")
    public ResponseEntity<UserDto> getMember(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(memberService.getMember(id));
    }

    /*
    회원 삭제
     */
    @DeleteMapping("/member/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(memberService.deleteMember(id));
    }

    /*
    회원 수정
     */
    @PutMapping("/member/{id}")
    public ResponseEntity updateMember(@PathVariable("id") Long id, @RequestBody UserDto dto){
        return ResponseEntity.ok().body(memberService.updateMember(id, dto));
    }


    /*
    주문 목록 조회
     */
    @GetMapping("/order")
    public ResponseEntity<List<OrderListDto>> orderList(@RequestParam(value="type", required = false) @Nullable String type,
                                                        @RequestParam(value="text", required = false) @Nullable String text,
                                                        @RequestParam(value="page", required = false, defaultValue = "0") @Nullable int page
    ){
        return ResponseEntity.ok().body(orderListService.getOrderList(type, text, page));
    }

    /*
    주문 상세 조회
     */
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderList> getOrderList(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(orderListService.getOrder(id));
    }

    /*
    주문 삭제
     */
    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOrderList(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(orderListService.deleteOrderList(id));
    }

    /*
    주문 수정
     */
    @PutMapping("/order/{id}")
    public ResponseEntity updateOrderList(@PathVariable("id") Long id, @RequestBody OrderListUpdateDto dto){
        System.out.println(dto.getOrderListStatus());
        return ResponseEntity.ok().body(orderListService.updateOrderList(id, dto));
    }

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
        ProductCategory productCategory = dto.getProductCategory();
        SearchCategory searchCategory = dto.getSearchCategory();


        List<Product> result = adminService.findProductsBy(searchCategory, productCategory, searchKeyword, page, pageSize);
        return ResponseEntity.ok(result);
    }


}
