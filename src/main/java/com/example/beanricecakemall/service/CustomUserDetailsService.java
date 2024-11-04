package com.example.beanricecakemall.service;

import com.example.beanricecakemall.customDTO.CustomUserDetails;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        //주어진 사용자 이름에 해당하는 사용자 정보를 db에서 찾아와서 UserDeatils타입으로 변환
        //사용자 이름을 입력받아 해당하는 사용자의 상세 정보 반환
        UserEntity user = userRepository.findByUserId(user_id);
        if (user != null) {
            System.out.println("userService에서 customUserDetails 객체 생성");
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("UsernameNotFoundException : " + user_id);
    }

}
