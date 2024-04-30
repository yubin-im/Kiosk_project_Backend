package com.study.springboot.domain.user.service;


import com.study.springboot.config.JwtUtil;
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
import org.springframework.http.ResponseEntity;
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
    private final JwtUtil jwtUtil;




    @Transactional
    public User createUser(String userId, String userPw, String userName){
        String pw = bCryptPasswordEncoder.encode(userPw);
        User user = User.makeUser(userId, pw, userName);

        User result = userRepository.save(user);

        try{
            if(result == null) throw new RuntimeException("멤버 추가 오류");
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findByUserId(String userId){
        return userRepository.findByUserId(userId).map(UserDto::new);
    }

    public Boolean passwordMatchesUserId(UserDto user, String userPw){
        if(bCryptPasswordEncoder.matches(userPw, user.getUserPw())){
            return true;
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findByUserIdAndPw(String userId, String userPw){
        User userEntity = Optional.ofNullable(
                userRepository.findByUserId(userId)).get().orElseThrow(()-> new BadCredentialsException("유저 없음"));

        if(!bCryptPasswordEncoder.matches(userPw, userEntity.getPassword())){

            //throw new BadCredentialsException("비밀번호 틀림");
            return null;
        }

        UserDto dto = new UserDto(userEntity);
        return Optional.of(dto);
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



    public Message setUserRegisterMessage(String userId, String userPw, String userName){
        Optional<UserDto> optional = findByUserId(userId);

        //이미 있는 회원
        if(optional.isPresent()){
            Message message = messageService.userRegisterUserIdExists();
            return message;
        }

        //회원가입 성공
        User newUser = createUser(userId, userPw, userName);
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
