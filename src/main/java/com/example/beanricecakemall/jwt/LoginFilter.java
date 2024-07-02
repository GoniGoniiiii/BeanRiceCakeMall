package com.example.beanricecakemall.jwt;

import com.example.beanricecakemall.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    //로그인 필터 클래스인디 그런데 UsernamePasswordAuthenticationFilter를 상속받은

    //주입받기 위해서 생성
    private AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;


    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response){

//        클라이언트 요청해서 username,password 추출
            String user_id=obtainUsername(request);
            String user_pw=obtainPassword(request);

//        token객체 생성해서 담음
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user_id,user_pw,null);
        return authenticationManager.authenticate(authToken);
    }




    //로그인 성공시 실행하는 메소드 -> 여기서 jwt 발급하면 됨
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,Authentication authentication){
        //user 객체를 알아내기 위해서 customUserDetails() 객체 생성
        //authentication.getPrincipal() 메소드를 통해서 유저 확인 가능 - > 타입 오류나기때문에 변경해줘야함.
        CustomUserDetails customUserDetails=(CustomUserDetails) authentication.getPrincipal();

        //userId 뽑아내기
        String user_id=customUserDetails.getUsername();

        //userid, role값을 authentication 객체로부터 뽑아냄
        //뽑아낸 userId와 role값을 가지고 jwtUtil에 token을 생성해달라고 전달할것임
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        Iterator<? extends  GrantedAuthority> iterator=authorities.iterator();
        GrantedAuthority auth=iterator.next();

        String user_role=auth.getAuthority();

        String token= jwtUtil.createJwt(user_id,user_role,60*60*10000L);

        response.addHeader("Authorization","Bearer " + token);
    }
    
    //로그인 실패시 실행하는 메소드
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        response.setStatus(401);

    }
}
