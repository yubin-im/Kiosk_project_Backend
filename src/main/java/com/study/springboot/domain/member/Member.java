package com.study.springboot.domain.member;


import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.enumeration.CategoryProduct;
import com.study.springboot.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table

public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

    @Column
    private String userName;

    @Column
    private String memberPw;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate;

    @Column
    private Integer memberPoint;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole;

    @OneToMany(mappedBy = "member")
    private List<OrderList> orderList;

}
