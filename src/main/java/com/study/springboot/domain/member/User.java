package com.study.springboot.domain.member;


import com.study.springboot.domain.orderSystem.OrderList;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(name="ID_MEMBER_ID_PW_CONSTRAINT", columnNames = {"id", "member_id", "member_pw"})
})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    @Column
    private String userName;


    @Column(nullable = false)
    private String memberPw;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate;

    @Column
    private Integer memberPoint;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<OrderList> orderList;

}
