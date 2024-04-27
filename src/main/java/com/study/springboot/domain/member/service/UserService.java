package com.study.springboot.domain.member.service;


import com.study.springboot.datas.KioskSession;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.member.User;
import com.study.springboot.domain.member.dto.RequestAddUserDto;
import com.study.springboot.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public User addUser(String userId, String userPw, String userName){
        User user = User.makeUser(userId, userPw, userName);

        User result = userRepository.save(user);

        try{
            if(result == null) throw new RuntimeException("멤버 추가 오류");
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public Message setUserLoginMessage(String userId, String userPw){

        Optional<User> optional = findByUserId(userId);

        //유저  not found
        if(!optional.isPresent()){
            Message message = Message.userNotFound();
            return message;
        }

        User user = optional.get();

        //비밀번호 불일치
        if(!user.isUserPw(user.getUserPw())){
            //로그인에 실패하였습니다
            Message message = Message.userPwInvalid();
            return message;
        }

        //로그인 성공
        KioskSession userSession = KioskSession.setSession(user);
        Message message = Message.userLoginSuccess();

        return message;
    }

    public Message setUserRegisterMessage(String userId, String userPw, String userName){
        Optional<User> optional = findByUserId(userId);

        //이미 있는 회원
        if(optional.isPresent()){
            Message message = Message.userRegisterUserIdExists();
            return message;
        }

        //회원가입 성공
        User newUser = addUser(userId, userPw, userName);
        Message message = Message.userRegisterSuccess();

        return message;
    }


}
