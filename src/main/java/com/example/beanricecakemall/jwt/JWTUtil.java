package com.example.beanricecakemall.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;
    
    //생성자에서 application.properties에 저장해놓은 키를 불러와서 객체키를 만들것이다
    public JWTUtil(@Value("${spring.jwt.secret}") String secret){
        this.secretKey=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //검증을 진행할 메소드
    public String getUserId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("user_id",String.class);
    }

    public String getUserRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("user_role",String.class);
    }

    //현재 시간을 넣어서 토큰이 만료되었는지 확인
    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    //토큰을 생성하는 메소드
    public String createJwt(String user_id,String user_role,Long expiredMs){
        return Jwts.builder()
                .claim("user_id",user_id)
                .claim("user_role",user_role)
                .issuedAt(new Date(System.currentTimeMillis())) //현재 발행시간 추가
                .expiration(new Date(System.currentTimeMillis()+expiredMs))//토큰이 언제 소멸할지
                .signWith(secretKey) //암호화 진행
                .compact();

    }
}
