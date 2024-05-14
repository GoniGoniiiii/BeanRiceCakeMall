package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
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
        userRepository.save(user);
    }

}
