package com.study.springboot.datas;

import com.study.springboot.domain.user.User;
import com.study.springboot.enumeration.UserRole;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KioskSession {
    private Boolean isLogin = false;
    private String userId;
    private UserRole userRole;


    @Builder
    public KioskSession(Boolean isLogin, String userId, UserRole userRole) {
        this.isLogin = isLogin;
        this.userId = userId;
        this.userRole = userRole;
    }

    public void login(){
        isLogin = true;
    }

    public static KioskSession makeAdminSession(String userId){
        return KioskSession.builder()
                .userId(userId)
                .userRole(UserRole.ADMIN)
                .build();
    }

    public static KioskSession makeUserSession(String userId){
        return KioskSession.builder()
                .userId(userId)
                .userRole(UserRole.USER)
                .build();
    }

    public static KioskSession setSession(User user){
        KioskSession session = null;

        if(user.isAdmin()){
            session = KioskSession.makeAdminSession(user.getUserId());
        }
        else{
            session = KioskSession.makeUserSession(user.getUserId());
        }

        session.login();
        return session;
    }

    private Boolean isAdmin(){
        return this.userRole.equals(UserRole.ADMIN);
    }

    public static Boolean isAdmin(HttpSession session){
        KioskSession kioskSession = (KioskSession) session.getAttribute("session");
        return (kioskSession != null && kioskSession.isAdmin());
    }


}
