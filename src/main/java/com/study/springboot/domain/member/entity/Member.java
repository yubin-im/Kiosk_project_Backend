package com.study.springboot.domain.member.entity;

import com.study.springboot.domain.orders.entity.Orders;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pw")
    private String memberPw;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private UserRole memberRole;

    @Column(name = "member_join_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate;

    @Column(name = "member_point")
    private int memberPoint;

    // member: orders = 1 : n
    @OneToMany(mappedBy = "member")
    private List<Orders> ordersList = new ArrayList<>();
}
