package com.study.springboot.domain.member;


import com.study.springboot.domain.member.dto.RequestLoginDto;
import com.study.springboot.domain.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public HttpStatus userLogin(RequestLoginDto dto){
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();
        Optional<User> optional = userService.findByUserId(userId);

        if(!optional.isPresent()){
            return HttpStatus.NOT_FOUND;
        }

        User user = optional.get();
        return (user.getUserPw().equals(userPw)) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }



//    @PostMapping("/register")
//    public HttpStatus ad
}
