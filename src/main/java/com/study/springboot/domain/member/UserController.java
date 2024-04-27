package com.study.springboot.domain.member;


import com.study.springboot.datas.KioskSession;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.member.dto.RequestAddUserDto;
import com.study.springboot.domain.member.dto.RequestLoginDto;
import com.study.springboot.domain.member.service.UserService;
import com.study.springboot.enumeration.error.StatusCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Message> userLogin(@RequestBody RequestLoginDto dto, HttpSession session){
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();

        //응답 메세지 생성
        Message message = userService.setLoginMessage(userId, userPw);

        //생성된 응답코드에 따라 session 생성
        StatusCode statusCode = message.getStatus();
        KioskSession kioskSession = null;

        //응답코드가 관리자 로그인
        if(statusCode.equals(StatusCode.ADMIN_LOGIN)){
            kioskSession = KioskSession.makeAdminSession(userId);
        }
        //응답코드가 일반 유저 로그인
        else if(statusCode.equals(StatusCode.USER_LOGIN)){
            kioskSession = KioskSession.makeUserSession(userId);
        }

        return ResponseEntity.ok(message);
    }




    @PostMapping("/register")
    public ResponseEntity<Message> addUser(@RequestBody RequestAddUserDto dto){

        String userId = dto.getUserId();
        String userPw = dto.getUserPw();
        String userName = dto.getUserName();

        Message message = userService.setUserRegisterMessage(userId, userPw, userName);

        return ResponseEntity.ok(message);
    }
}
