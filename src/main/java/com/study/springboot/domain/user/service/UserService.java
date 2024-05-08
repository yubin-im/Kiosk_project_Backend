package com.study.springboot.domain.user.service;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.config.JwtUtil;
import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;
import com.study.springboot.domain.user.QUser;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MessageService messageService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JPAQueryFactory jpaQueryFactory;





    /*
    회원가입
     */

    @Transactional
    public Optional<User> createUser(String userId, String userPw, String userName){
        QUser user = QUser.user;

        Long count = jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(userId))
                .stream().count();

        if(count > 0){
            return Optional.ofNullable(null);
        }


        String pw = bCryptPasswordEncoder.encode(userPw);
        User newUser = User.makeUser(userId, pw, userName);

        User result = userRepository.save(newUser);

        try{
            if(result == null) throw new RuntimeException("멤버 추가 오류");
        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.of(result);
    }


    /*
    userId로 user찾기
     */
    @Transactional(readOnly = true)
    public Optional<UserDto> findByUserId(String userId){

        Optional<User> optional = userRepository.findByUserId(userId);
        System.out.println("bool " + optional.get().getUserDelYn());

        if(optional.isEmpty() || optional.get().getUserDelYn() ==  null) {
            return Optional.empty();
        }
        if(optional.get().getUserDelYn()) {
            return Optional.empty();
        }

        return userRepository.findByUserId(userId).map(UserDto::new);
    }


    /*
    userPw와 inputPw 일치하는지 확인
     */
    public Boolean passwordMatchesUserId(UserDto user, String inputPw){
        if(bCryptPasswordEncoder.matches(inputPw, user.getUserPw())){
            return true;
        }

        return false;
    }




    /*
    userId 와 userPw 로 user 찾기
     */
    @Transactional(readOnly = true)
    public Optional<UserDto> findByUserIdAndPw(String userId, String userPw){
        User userEntity = Optional.ofNullable(
                userRepository.findByUserId(userId)).get().orElseThrow(()-> new BadCredentialsException("유저 없음"));

        if(!bCryptPasswordEncoder.matches(userPw, userEntity.getPassword())){

            //throw new BadCredentialsException("비밀번호 틀림");
            return Optional.ofNullable(null);
        }

        UserDto dto = new UserDto(userEntity);
        return Optional.of(dto);
    }

}
