package com.example.beanricecakemall.jwt;

import com.example.beanricecakemall.dto.CustomUserDetails;
import com.example.beanricecakemall.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //token 검증

        //request에서 header를 찾음
        String authorization=request.getHeader("Authorization");

        //Authorization 헤더 검증(null인지 아닌지)
        if(authorization == null || !authorization.startsWith("Bearer ")){
            System.out.println("token null");
            filterChain.doFilter(request,response);
            //조건이 해당되면 메소드 종료(필수)
            return ;
        }
        System.out.println(authorization);

        //접두사 제거를 위해 띄어쓰기로 앞 뒤 구분하고 뒷부분을 token에 저장
        String token=authorization.split(" ")[1];

        //토큰 소멸시간 검증
        if(jwtUtil.isExpired(token)){
            System.out.println("token 만료됨");
            filterChain.doFilter(request,response);

            return ;
        }

        //토큰에서 user_id와 user_role 얻어오기
        String user_id= jwtUtil.getUserId(token);
        String user_role= jwtUtil.getUserRole(token);

        //userEntity를 생성하여 값 set
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(user_id);
        userEntity.setUserRole(user_role);
        userEntity.setUserPw("dfsfdsfs");

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails=new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken=new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }
}
