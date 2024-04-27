package com.study.springboot.domain.member;


import com.study.springboot.datas.KioskSession;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.member.dto.RequestAddUserDto;
import com.study.springboot.domain.member.dto.RequestLoginDto;
import com.study.springboot.domain.member.service.UserService;
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

        Message message = userService.setUserLoginMessage(userId, userPw);

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
