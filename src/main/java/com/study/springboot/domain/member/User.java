package com.study.springboot.domain.member;


import com.study.springboot.domain.member.dto.UserDto;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(name="ID_USER_ID_PW_CONSTRAINT", columnNames = {"id", "user_id", "user_pw"})
})
public class User implements UserDetails {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String userId;


    @Column
    private String userName;


    @Getter
    @Column(nullable = false)
    private String userPw;


    @Getter
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userJoinDate;


    @Getter
    @Column
    private Integer userPoint = 0;


    @Getter
    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole = UserRole.USER;


    @Getter
    @OneToMany(mappedBy = "user")
    private List<OrderList> orderList = new ArrayList<>();


    @Builder
    private User(Long id, String userId, String userName, String userPw, LocalDate userJoinDate, Integer userPoint, UserRole userRole, List<OrderList> orderList) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
        this.userJoinDate = userJoinDate;
        this.userPoint = userPoint;
        this.userRole = userRole;
        this.orderList = orderList;
    }

    public String getUserName() {
        return userName;
    }

    public static User makeAdminUser(String userId, String userName, String userPw){
        User user = User.builder()
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .userRole(UserRole.ADMIN)
                .userJoinDate(LocalDate.now())
                .build();

        return user;
    }

    public static User makeUser(String userId, String userPw, String userName){
        User user = User.builder()
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .userRole(UserRole.USER)
                .userJoinDate(LocalDate.now())
                .userPoint(0)
                .build();

        return user;
    }

    public User updateUserInfo(String userId, String userPw, String userName){
        return User.builder()
                .id(this.id)
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .userJoinDate(this.userJoinDate)
                .userRole(this.userRole)
                .orderList(this.orderList)
                .userPoint(this.userPoint)
                .build();
    }


    public User addUserPoint(Integer point){
        Integer updatedPoint = this.userPoint + point;
        return User.builder()
                .id(this.id)
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .userJoinDate(this.userJoinDate)
                .userRole(this.userRole)
                .orderList(this.orderList)
                .userPoint(updatedPoint)
                .build();
    }

    public User updateUserJoinDate(LocalDate userJoinDate){
        return User.builder()
                .id(this.id)
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .userJoinDate(userJoinDate)
                .userRole(this.userRole)
                .orderList(this.orderList)
                .userPoint(this.userPoint)
                .build();
    }

    public Boolean isAdmin(){
        return this.userRole.equals(UserRole.ADMIN);
    }

    public Boolean isUserPw(String userPw){
        return this.userPw.equals(userPw);
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole.getValue()));

        return roles;
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
