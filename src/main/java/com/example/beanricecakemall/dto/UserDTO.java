package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private int user_num;

    private String user_id;

    private String user_pw;

    private String user_name;

    private String user_email;

    private String user_birth;

    private String user_registraion;

    private Timestamp user_joindate;

    private int user_point;

    private String user_role;

    private String user_tel;

    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO=new UserDTO();
        userDTO.setUser_num(userEntity.getUserNum());
        userDTO.setUser_id(userEntity.getUserId());
        userDTO.setUser_pw(userEntity.getUserPw());
        userDTO.setUser_name(userEntity.getUserName());
        userDTO.setUser_email(userEntity.getUserEmail());
        userDTO.setUser_birth(userEntity.getUserBirth());
        userDTO.setUser_joindate(userEntity.getUserJoindate());
        userDTO.setUser_registraion(userEntity.getUserRegistration());
        userDTO.setUser_point(userEntity.getUserPoint());
        userDTO.setUser_role(userEntity.getUserRole());
        userDTO.setUser_tel(userEntity.getUserTel());
        return userDTO;
    }

}
