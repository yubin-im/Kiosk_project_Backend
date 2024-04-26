package com.study.springboot.domain.member;


import com.study.springboot.datas.KioskSession;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.member.dto.RequestAddUserDto;
import com.study.springboot.domain.member.dto.RequestLoginDto;
import com.study.springboot.domain.member.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public HttpStatus userLogin(@RequestBody RequestLoginDto dto, HttpSession session){
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();
        Optional<User> optional = userService.findByUserId(userId);

        if(!optional.isPresent()){
            return HttpStatus.NOT_FOUND;
        }

        User user = optional.get();

        if(!user.isUserPw(userPw)){
            return HttpStatus.NOT_FOUND;
        }

        KioskSession userSession = userService.setSession(user);
        return (user.getUserPw().equals(userPw)) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }




    @PostMapping("/register")
    public ResponseEntity<Message> addUser(@RequestBody RequestAddUserDto dto){

        String userId = dto.getUserId();

        Optional<User> optional = userService.findByUserId(userId);

        if(optional.isPresent()){
            Message message = Message.builder()
                    .status("fail")
                    .message("이미 있는 사용자")
                    .build();
            return ResponseEntity.ok(message);
        }

        User newUser = userService.addUser(dto);
        Message message = Message.builder()
                .status("ok")
                .message("등록 성공")
                .build();

        return ResponseEntity.ok(message);
    }


}
