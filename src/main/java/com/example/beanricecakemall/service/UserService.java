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
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean exist(String user_id) {
        System.out.println(user_id);
        boolean exist = userRepository.existsByUserId(user_id);
        System.out.println("id 존재 여부 : " + exist);

        if (exist) {
            System.out.println("id가 이미 존재함");
            return true;
        }
        return false;
    }

    public String join(UserDTO userDTO) {
        UserEntity user = UserEntity.toSaveEntity(userDTO);
        user.setUserPw(bCryptPasswordEncoder.encode(user.getUserPw()));
        user.setUserRole("ROLE_USER");
        user.setUserRegistration("N");

        userRepository.save(user);
        return "사용 가능한 아이디 입니다!";
    }


    public UserDTO memberInfo(String user_id) {
        UserEntity userEntity = userRepository.findByUserId(user_id);
        UserDTO user = UserDTO.toUserDTO(userEntity);
        return user;
    }

    public void update(UserDTO userDTO) {
        UserEntity user = UserEntity.toUpdateEntity(userDTO);
        user.setUserPw(bCryptPasswordEncoder.encode(user.getUserPw()));
        user.setUserRole("ROLE_USER");
        user.setUserRegistration("N");

        userRepository.save(user);
    }

    public void delete(int user_num) {
        userRepository.deleteById(user_num);
    }

    public String findId(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        String user_id = null;
        String user_name = userDTO.getUser_name();
        String user_tel = userDTO.getUser_tel();
        String user_email = userDTO.getUser_email();
        System.out.println("user_name :" + user_name + "user_tel : " + user_tel + "user_email :" + user_email);

        if (user_tel != null && !user_tel.equals("--")) {
            userEntity = userRepository.findByUserNameAndUserTel(user_name, user_tel);
        } else if (user_email != null && !user_email.equals("--")) {
            userEntity = userRepository.findByUserNameAndUserEmail(user_name, user_email);
        }

        if (userEntity != null) {
            user_id = userEntity.getUserId();
        } else {
            user_id = "찾을수 없음";
        }
        return user_id;
    }

    public String findPw(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        String user_pw = null;
        String user_id = userDTO.getUser_id();
        String user_name = userDTO.getUser_name();
        String user_tel = userDTO.getUser_tel();
        String user_email = userDTO.getUser_email();
        System.out.println("user_name :" + user_name + "user_id : " + user_id + "user_tel : " + user_tel + "user_email :" + user_email);

        if (user_tel != null && !user_tel.equals("--")) {
            userEntity = userRepository.findByUserIdAndUserNameAndUserTel(user_id, user_name, user_tel);
        } else if (user_email != null) {
            userEntity = userRepository.findByUserIdAndUserNameAndUserEmail(user_id, user_name, user_email);
        }

        if (userEntity != null) {
            int user_num = userEntity.getUserNum();
            System.out.println("service user_num : " + user_num);

            //비밀번호는 암호화 되어있기때문에 랜덤으로 초기화 해준후 사용자에게 뿌려주기
            Random random = new Random();
            int secret = random.nextInt(1000000000);

            // 기존 userEntity 업데이트
            userEntity.setUserPw(bCryptPasswordEncoder.encode(String.valueOf(secret)));
            userRepository.save(userEntity);

            user_pw = String.valueOf(secret);
        } else {
            user_pw = "찾을 수 없음";
        }

        return user_pw;
    }


    //유저 id값을 넣어서 유저 일련번호 가져오기
    public int findUserNum(String user_id){
        UserEntity userEntity=userRepository.findUserNumByUserId(user_id);
        int user_num=userEntity.getUserNum();
        return user_num;
    }

    public UserDTO findUserInfo(int user_num) {
        UserEntity userEntity=userRepository.findByUserNum(user_num);
        return UserDTO.toUserDTO(userEntity);
    }
}