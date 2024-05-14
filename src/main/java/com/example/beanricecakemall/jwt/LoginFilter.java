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

    //public Authentication authentication(HttpServletRequest request,HttpServletResponse){

        //클라이언트 요청해서 username,password 추출
       //     String userId=obtainUsername(request);
          //  String userPw=obtainPassword(request);


        //꺼낸값으로 인증 진행할것임 UsernamePasswordAuthenticationFilter가 autenticationManager한테  username,password를 던져줌
        //그냥 던져주는게 아니고 dto라는 바구니에 담아서 던져주는것임
        //바구니가 UserNamePassowrdauthenticationToken에 username,password를 담아서 최종적으로 authenticationManager한테 전달

        //token객체 생성해서 담음

   // }




    //로그인 성공시 실행하는 메소드 -> 여기서 jwt 발급하면 됨

    //로그인 실패시 실행하는 메소드

}
