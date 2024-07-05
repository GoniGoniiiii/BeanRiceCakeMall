package com.example.beanricecakemall.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil{

    private SecretKey secretKey;

    //생성자에서 application.properties에 저장해놓은 키를 불러와서 객체키를 만듦
    public JWTUtil(@Value("${spring.jwt.secret}") String secret){
        this.secretKey=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //검증을 진행할 메소드
    public String getUserId(String token){
        //토큰을 암호화가 진행돼있으니 우선 검증해야됨
        //verifywith:  우리가 가지고 있는 secretkey를 넣어서 토큰이 우리 서버에서 생성되었는지,우리가 갖고있는 키와 맞는지 확인후 builder타입으로 리턴해줌
        //parseSignedClaims : 클레임을 확인한대
        //get에서 가져올 타입을 String으로 지정해주면 String 타입의 get username을 진행할 수있때
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("user_id",String.class);
    }

    public String getUserRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(("user_role"),String.class);
    }

    public Boolean isExpired(String token){
        //위에  메소드 들과 달리 현재시간을 넣어서 만료된건지 아닌지 확인해줌
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());

    }


    //토큰을 생성하는 메소드
    public String  createJWT(String user_id,String user_role, Long expiredMs){
        return Jwts.builder()
                .claim("user_id", user_id)
                .claim("user_role" ,user_role)
                .issuedAt(new Date(System.currentTimeMillis())) //현재 발행시간 추가해줌
                .expiration(new Date(System.currentTimeMillis()+expiredMs)) //토큰이 언제 소멸할지ㄴ
                .signWith(secretKey) //암호화 진행
                .compact();
    }
}
