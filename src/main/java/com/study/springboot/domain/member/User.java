package com.study.springboot.domain.member;


import com.study.springboot.domain.member.dto.UserDto;
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
        @UniqueConstraint(name="ID_USER_ID_PW_CONSTRAINT", columnNames = {"id", "user_id", "user_pw"})
})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column
    private String userName;


    @Column(nullable = false)
    private String userPw;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userJoinDate;

    @Column
    private Integer userPoint;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<OrderList> orderList;

    public void update(UserDto dto){
        this.userId=dto.getUserId();
        this.userPw= dto.getUserPw();
        this.userName=dto.getUserName();
        this.userJoinDate=dto.getUserJoinDate();
        this.userPoint=dto.getUserPoint();
        if(dto.getUserRole().equals("ROLE_USER")){
            this.userRole=UserRole.USER;
        } else{
            this.userRole=UserRole.ADMIN;
        }
    }
}
