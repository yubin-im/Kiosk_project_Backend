package com.study.springboot.domain.user;


import com.study.springboot.config.JwtUtil;
import com.study.springboot.datas.Message;


import com.study.springboot.datas.MessageService;
import com.study.springboot.datas.UserToken;
import com.study.springboot.domain.user.dto.RequestAddUserDto;
import com.study.springboot.domain.user.dto.RequestLoginDto;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<Message> userLogin(@ModelAttribute RequestLoginDto dto){
        Optional<UserDto> isMember = userService.findByUserId(dto.getUserId());

        Message message = null;

        //멤버가 존재하지 않는다면
        if(isMember.isEmpty()){
            message = messageService.userNotFound();
            return ResponseEntity.ok(message);
        }


        //멤버가 존재한다면

        // 멤버가 존재하지만 비밀번호가 불일치
        UserDto user = isMember.get();
        if(!userService.passwordMatchesUserId(user, dto.getUserPw())){
            message = messageService.userPwInvalid();
            return ResponseEntity.ok(message);
        }


        String token = jwtUtil.createToken(user.getUserId(), user.getUserRole());

        UserToken userToken = UserToken.makeUserToken(token, user.getUserId());
        message = messageService.userLoginSuccess(userToken);

        return ResponseEntity.ok(message);
    }






    @PostMapping("/register")
    public ResponseEntity<Message> createUser(@RequestBody RequestAddUserDto dto){

        String userId = dto.getUserId();
        String userPw = dto.getUserPw();
        String userName = dto.getUserName();

        Optional<User> user = userService.createUser(userId, userPw, userName);

        //가입 실패했다면
        if(!user.isPresent()){
            Message message = messageService.userRegisterFailed();
            return ResponseEntity.ok(message);
        }

        Message message = messageService.userRegisterSuccess();

        return ResponseEntity.ok(message);
    }
}
