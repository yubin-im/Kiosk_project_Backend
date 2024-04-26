package com.study.springboot.domain.member.service;


import com.study.springboot.domain.member.User;
import com.study.springboot.domain.member.dto.RequestLoginDto;
import com.study.springboot.domain.member.dto.RequestUserDto;
import com.study.springboot.domain.member.dto.UserDto;
import com.study.springboot.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public User addUser(RequestUserDto dto){
        User user = userRepository.save(dto.toEntity());

        try{
            if(user == null) throw new RuntimeException("멤버 추가 오류");
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }




}
