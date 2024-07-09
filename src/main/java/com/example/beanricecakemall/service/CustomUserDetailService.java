package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.CustomUserDetails;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        System.out.println("db에서 조회할 id   : "+ user_id);
        //db에서 특정 유저를 조회해서 리턴
        UserEntity user=userRepository.findByUserId(user_id);
        System.out.println("db에서 조회한 값 : " +user.getUserRole());
        System.out.println(user.toString());
        if(user != null){
            System.out.println("user값이 null이 아님");
            return new CustomUserDetails(user);
        }
        return null;
    }
}
