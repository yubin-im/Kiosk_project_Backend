package com.study.springboot.domain.admin;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.datas.AdminMessage;
import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.*;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.QProduct;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.RequestProductEditDto;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.domain.product.service.ProductService;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.dto.UserListDto;
import com.study.springboot.domain.user.repository.UserRepository;
import com.study.springboot.enumeration.OrderListStatus;
import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.SearchCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final MessageService messageService;
    private final OrderListRepository orderListRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    private final JPAQueryFactory queryFactory;


    /*
    User
     */

    //회원 목록 조회
    @Transactional
    public UserListDto getUserList(String type, String text, int page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("userJoinDate").descending());

        Page<UserDto> userDtoPage=null;
        if(type == null){
            userDtoPage = userRepository.findAll(pageRequest).map(UserDto::new);
        } else if(type.equals("id")){
            userDtoPage = userRepository.findByUserIdContains(text, pageRequest).map(UserDto::new);
        } else if(type.equals("name")){
            userDtoPage = userRepository.findByUserNameContains(text, pageRequest).map(UserDto::new);
        }
        return UserListDto.builder()
                .userDtoList(userDtoPage)
                .build();
    }

    //회원 상세 조회
    public UserDto getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("멤버가 존재하지 않습니다."));
        return new UserDto(user);
    }

    //회원 삭제
    @Transactional
    public boolean deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("멤버가 존재하지 않습니다."));
        if(user==null){
            return false;
        } else{
            userRepository.delete(user);
            return true;
        }
    }

    //회원 수정
    @Transactional
    public boolean updateUser(Long id, UserDto dto){
        User user = userRepository.findById(id).get();
        if(user==null || id!=user.getId()){
            return false;
        } else{
            user.update(dto);
            return true;
        }
    }


    /*
    Product
     */

    @Transactional(readOnly = true)
    public List<ProductDto> findAllProduct(){
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }


    @Transactional
    public Message setRemoveProductMessage(String productCode, String productName){
        Optional<Product> optional = productService.findByCode(productCode);

        //존재하지 않는 코드
        if(!optional.isPresent()){
            Message message = messageService.productNotFoundMessage();
            return message;
        }


        Product product = optional.get();

        //코드명과 코드번호 불일치
        if(!product.getProductName().equals(productName)){
            Message message = messageService.productCodeMisMatchMessage();
            return message;
        }

        //삭제 성공
        productRepository.delete(product);
        Message message = messageService.productRemoveSuccessMessage();
        return message;
    }

    @Transactional(readOnly = true)
    Optional<Product> findProductByCode(String code){
        return productRepository.findProductByProductCode(code);
    }


    @Transactional
    Message editProduct(RequestProductEditDto dto){
        Optional<Product> optional = productRepository.findProductByProductCode(dto.getProductCode());

        if(!optional.isPresent()){
            return messageService.productNotFoundMessage();
        }

        Product product = optional.get();
        Long id = product.getId();

        product = dto.toEntity(id);
        productRepository.save(product);

        return messageService.productEditSuccess();

    }

    @Transactional(readOnly = true)
    List<Product> findProductsBy(final SearchCategory searchCategory, final ProductCategory productCategory, final String searchKeyword, final int page, final int pageSize){


        QProduct product = QProduct.product;
        List<Product> list = queryFactory.selectFrom(product)
                .where(
                        product.category.stringValue().eq(productCategory.getValue())
                                .and(
                                        product.productCode.contains(searchKeyword)
                                                .or(product.productName.contains(searchKeyword))
                                )
                )
                .fetch();

        return list;

    }

//    private BooleanExpression eqSearchCategory(SearchCategory)


    /*
    OrderList
     */

    //주문 목록 조회
    @Transactional
    public List<OrderListDto> getOrderList(String type, String text, int page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("orderListTime").descending());

        Page<OrderListDto> orderListDtoPage=null;
        if(type==null){
            orderListDtoPage = orderListRepository.findAll(pageRequest).map(OrderListDto::new);
        } else if(type.equals("status")){
            OrderListStatus status = OrderListStatus.valueOf(text.toUpperCase());
            orderListDtoPage = orderListRepository.findByOrderListStatus(status, pageRequest).map(OrderListDto::new);
        }

        return orderListDtoPage.getContent();
    }

    //주문 상세 조회
    public OrderList getOrder(Long id){
        OrderList orderList = orderListRepository.findById(id).orElseThrow(()->new IllegalArgumentException("주문이 존재하지 않습니다."));
        System.out.println(orderList.toString());
        return orderList;
    }

    //주문 삭제
    @jakarta.transaction.Transactional
    public boolean deleteOrderList(Long id){
        OrderList orderList = orderListRepository.findById(id).orElseThrow(()->new IllegalArgumentException("주문이 존재하지 않습니다."));

        if(orderList==null){
            return false;
        } else{
            orderListRepository.delete(orderList);
            return true;
        }
    }

    //주문 수정
    @Transactional
    public boolean updateOrderList(Long id, OrderListUpdateDto dto){
        OrderList orderList = orderListRepository.findById(id).get();
        System.out.println(dto.getOrderListStatus());
        if(orderList==null || id!=dto.getId()){
            return false;
        } else {
            orderList.update(dto.getOrderListTime(),dto.getOrderListTotalPrice(), OrderListStatus.valueOf(dto.getOrderListStatus()));
            return true;
        }
    }

    //주문 상세 수정
    @Transactional
    public Message updateOrderItem(Long orderListId,OrderItemUpdateRequestDto dto){
        Optional<OrderItem> optional = orderItemRepository.findById(dto.getId());

        if(!optional.isPresent()){
            return AdminMessage.orderItemNotFoundMessage();
        }

        OrderItem orderItem = optional.get();

        if(orderItem.getOrderList().getId()!=orderListId){
            return AdminMessage.orderItemOrderListMisMatchMessage();
        }

        orderItem.updateAmountAndPrice(dto.getOrderItemAmount(), dto.getOrderItemPrice());

        return AdminMessage.productEditSuccess();
    }

    //주문 통계 - 날짜별 주문 수입 조회
    public OrderRevenueListDto getOrderRevenue(String type, int year, int month){

        List<OrderRevenueResponseDto> result = new ArrayList<>();
        List<Object[]> summary = null;

        if(type.equals("month")){
            // 일별 통계
            summary = orderListRepository.findOrderMonth(year, month);
        } else if(type.equals("year")){
            // 월별 통계
            summary = orderListRepository.findOrderYear(year);
        }

        for(Object[] dto : summary){
            OrderRevenueResponseDto resDto = OrderRevenueResponseDto.builder()
                    .orderListDate(dto[0].toString())
                    .orderListTotalPrice(dto[1].toString())
                    .build();
            result.add(resDto);
        }

        return OrderRevenueListDto.builder()
                .type(type)
                .year(year)
                .month(month)
                .OrderRevenueList(result)
                .build();
    }

    //주문 통계 - 날짜별 주문 수 조회
    public OrderCountListDto getOrderCount(String type, int year, int month){

        List<OrderCountResponseDto> result = new ArrayList<>();
        List<Object[]> summary = null;

        if(type.equals("month")){
            // 일별 통계
            summary = orderListRepository.findOrderMonth(year, month);
        } else if(type.equals("year")){
            // 월별 통계
            summary = orderListRepository.findOrderYear(year);
        }

        for(Object[] dto : summary){
            OrderCountResponseDto resDto = OrderCountResponseDto.builder()
                    .orderListDate(dto[0].toString())
                    .orderListCount(dto[2].toString())
                    .build();
            result.add(resDto);
        }

        return OrderCountListDto.builder()
                .type(type)
                .year(year)
                .month(month)
                .OrderCountList(result)
                .build();
    }
}

