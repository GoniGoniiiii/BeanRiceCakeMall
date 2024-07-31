package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO implements OAuth2User {

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

    private int user_zipcode;

    private String user_addr;

    private String user_addrDetail;

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
        userDTO.setUser_zipcode(userEntity.getUserZipcode());
        userDTO.setUser_addr(userEntity.getUserAddr());
        userDTO.setUser_addrDetail(userEntity.getUserAddrdetail());
        return userDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
