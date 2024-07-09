package com.example.beanricecakemall.jwt;

import com.example.beanricecakemall.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    //로그인 필터 클래스인디 그런데 UsernamePasswordAuthenticationFilter를 상속받은

    //주입받기 위해서 생성
    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //      클라이언트 요청해서 username,password 추출
        String userId=obtainUsername(request);
        String userPw = obtainPassword(request);
        System.out.println("attemptAuthentication :" + userId);
        System.out.println("attemptAuthentication :" + userPw);

//        token객체 생성해서 담음
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, userPw, null);
        System.out.println(authToken.toString());
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 -> 여기서 jwt 발급하면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        //user 객체를 알아내기 위해서 customUserDetails() 객체 생성
//    //authentication.getPrincipal() 메소드를 통해서 유저 확인 가능 - > 타입 오류나기때문에 변경해줘야함.
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//
//     //userId 뽑아내기
        String user_id = customUserDetails.getUsername();
        System.out.println("로그인이 성공하면 나오는 user_id : " + user_id);

        //userid, role값을 authentication 객체로부터 뽑아냄
        //뽑아낸 userId와 role값을 가지고 jwtUtil에 token을 생성해달라고 전달할것임
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends  GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String user_role = auth.getAuthority();
        System.out.println(user_role);

        String token = jwtUtil.createJWT(user_id, user_role, 60 * 60 * 1000L);

        //HTTP 인증방식은 RFC 7235의 정의에 따라 아래와 같은 형태를 가져야한대
        response.addHeader("Authorization", "Bearer " + token);
    }

       //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.sendRedirect("/login");
    }

}
