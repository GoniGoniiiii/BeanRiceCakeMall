package com.example.beanricecakemall.customDTO;

import com.example.beanricecakemall.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    //UserDetails 인터페이스를 구현한 클래스 , UserEntity를 기반으로 사용자의 상세 정보 제공

    public UserEntity userEntity;

    //UserEntity 객체를 필드로 가짐 -> UserEntity 정보에 접근 가능
    public CustomUserDetails(UserEntity userEntity){
        this.userEntity=userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //UserEntity에서 가져온 역할 정보를 GrantedAuthority타입으로 반환
        //GrantedAuthority : 스프링 시큐리티에서 권한을 나타내는 인터페이스
        Collection<GrantedAuthority> collection=new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getUserRole();
            }
        });
        return collection;
    }

    public int getUserNum(){
        return userEntity.getUserNum();
    }

    @Override
    public String getPassword() {
        return userEntity.getUserPw();
    }

    @Override
    public String getUsername() {
        return userEntity.getUserId();
    }


    //true값으로 바꿔줘야 만료되지않고 사용 가능
    @Override
    public boolean isAccountNonExpired() {
        //계정만료 X
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //잠금 X
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격증명 만료X
        return true;
    }

    @Override
    public boolean isEnabled() {
        //계정 활성화 O
        return true;
    }
}
