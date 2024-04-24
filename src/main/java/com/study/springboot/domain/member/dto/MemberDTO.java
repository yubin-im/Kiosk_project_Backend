package com.study.springboot.domain.member.dto;

import com.study.springboot.domain.member.entity.UserRole;
import com.study.springboot.domain.orders.entity.Orders;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPw;
    private String memberName;
    private UserRole memberRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate;
    private int memberPoint;
    private List<Orders> ordersList;
}
