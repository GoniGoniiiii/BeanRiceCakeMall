package com.example.beanricecakemall.service;

import com.example.beanricecakemall.customDTO.*;
import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(UserDTO userDTO){
        boolean exist=userRepository.existsByUserId(userDTO.getUser_id());
        System.out.println(exist);

        if(exist){
            System.out.println("id가 이미 존재함");
            return ;
        }

        UserEntity user=UserEntity.toSaveEntity(userDTO);
        user.setUserPw(bCryptPasswordEncoder.encode(user.getUserPw()));
        user.setUserRole("ROLE_USER");
        user.setUserRegistration("N");

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        //주어진 사용자 이름에 해당하는 사용자 정보를 db에서 찾아와서 UserDeatils타입으로 변환
        //사용자 이름을 입력받아 해당하는 사용자의 상세 정보 반환
        UserEntity user=userRepository.findByUserId(user_id);
        if(user!=null){
            System.out.println("userService에서 customUserDetails 객체 생성");
            return new CumstomUserDetails(user);
        }
        throw new UsernameNotFoundException("UsernameNotFoundException : "+ user_id);
    }


}
