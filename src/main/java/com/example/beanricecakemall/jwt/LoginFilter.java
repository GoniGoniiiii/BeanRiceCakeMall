package com.example.beanricecakemall.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    //로그인 필터 클래스인디 그런데 UsernamePasswordAuthenticationFilter를 상속받은

    //주입받기 위해서 생성
    private AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication authentication(HttpServletRequest request,HttpServletResponse response){

//        클라이언트 요청해서 username,password 추출
            String userId=obtainUsername(request);
            String userPw=obtainPassword(request);

//        token객체 생성해서 담음
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userId,userPw,null);
        return authenticationManager.authenticate(authToken);
    }




    //로그인 성공시 실행하는 메소드 -> 여기서 jwt 발급하면 됨
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,Authentication authentication){

    }

    //로그인 실패시 실행하는 메소드
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){

    }
}
