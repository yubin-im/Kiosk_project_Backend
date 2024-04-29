package com.study.springboot.domain.member.service;


import com.study.springboot.domain.member.dto.UserDto;
import com.study.springboot.domain.member.dto.UserListDto;
import com.study.springboot.domain.member.User;
import com.study.springboot.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    회원 목록 조회
     */
    @Transactional
    public UserListDto getMemberList(String type, String text, int page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("userJoinDate").descending());

        Page<UserDto> userDtoPage=null;
        if(type == null){
            userDtoPage = memberRepository.findAll(pageRequest).map(UserDto::new);
        } else if(type.equals("id")){
            userDtoPage = memberRepository.findByUserIdContains(text, pageRequest).map(UserDto::new);
        } else if(type.equals("name")){
            userDtoPage = memberRepository.findByUserNameContains(text, pageRequest).map(UserDto::new);
        }
        return UserListDto.builder()
                .userDtoList(userDtoPage)
                .build();
    }

    /*
    회원 상세 조회
     */
    public UserDto getMember(Long id){
        User user = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("멤버가 존재하지 않습니다."));
        return new UserDto(user);
    }

    /*
    회원 삭제
     */
    @Transactional
    public boolean deleteMember(Long id){
        User user = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("멤버가 존재하지 않습니다."));
        if(user==null){
            return false;
        } else{
            memberRepository.delete(user);
            return true;
        }
    }

    /*
    회원 수정
     */
    @Transactional
    public boolean updateMember(Long id, UserDto dto){
        User user = memberRepository.findById(id).get();
        if(user==null || id!=user.getId()){
            return false;
        } else{
            user.update(dto);
            return true;
        }

    }

}
