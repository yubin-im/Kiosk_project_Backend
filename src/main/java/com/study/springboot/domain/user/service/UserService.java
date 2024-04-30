package com.study.springboot.domain.user.service;


import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.user.dto.UserDto;
import com.study.springboot.domain.user.dto.UserListDto;
import com.study.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MessageService messageService;


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

    private Message setAdminLoginMessage(User admin, String adminPw){

        //비밀번호 틀림
        if(!admin.isUserPw(adminPw)){
            Message message = messageService.adminPwInvalid();
            return message;
        }

        Message message = messageService.adminLoginSuccess();
        return message;
    }

    private Message setUserLoginMessage(User user, String userPw){

        //만약 관리자일 경우 에러 발생
        try{
            if(user.isAdmin()){
                throw new RuntimeException("관리자입니다");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //비번 오류
        if(!user.isUserPw(userPw)){
            Message message = messageService.userPwInvalid();
            return message;
        }

        //로그인 성공
        Message message = messageService.userLoginSuccess();
        return message;
    }

    public Message setLoginMessage(String userId, String userPw){

        Optional<User> optional = userRepository.findByUserId(userId);

        //아이디가 없다면
        if(!optional.isPresent()){
            Message message = messageService.userNotFound();
            return message;
        }

        User user = optional.get();

        //관리자 로그인
        if(user.isAdmin()){
            Message message = setAdminLoginMessage(user, userPw);
            return message;

        }

        //일반 회원 로그인
        Message message = setUserLoginMessage(user, userPw);
        return message;
    }

    public Message setUserRegisterMessage(String userId, String userPw, String userName){
        Optional<User> optional = findByUserId(userId);

        //이미 있는 회원
        if(optional.isPresent()){
            Message message = messageService.userRegisterUserIdExists();
            return message;
        }

        //회원가입 성공
        User newUser = addUser(userId, userPw, userName);
        Message message = messageService.userRegisterSuccess();

        return message;
    }

    /*
    회원 목록 조회
     */
    @Transactional
    public UserListDto getUserList(String type, String text, int page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("userJoinDate").descending());

        Page<UserDto> userDtoPage=null;
        if(type == null){
            userDtoPage = userRepository.findAll(pageRequest).map(UserDto::new);
        } else if(type.equals("id")){
            userDtoPage = userRepository.findByUserIdContains(text, pageRequest).map(UserDto::new);
        } else if(type.equals("name")){
            userDtoPage = userRepository.findByUserNameContains(text, pageRequest).map(UserDto::new);
        }
        return UserListDto.builder()
                .userDtoList(userDtoPage)
                .build();
    }

    /*
    회원 상세 조회
     */
    public UserDto getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("멤버가 존재하지 않습니다."));
        return new UserDto(user);
    }

    /*
    회원 삭제
     */
    @Transactional
    public boolean deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("멤버가 존재하지 않습니다."));
        if(user==null){
            return false;
        } else{
            userRepository.delete(user);
            return true;
        }
    }

    /*
    회원 수정
     */
    @Transactional
    public boolean updateUser(Long id, UserDto dto){
        User user = userRepository.findById(id).get();
        if(user==null || id!=user.getId()){
            return false;
        } else{
            user.update(dto);
            return true;
        }
    }

}
