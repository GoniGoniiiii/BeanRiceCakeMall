package com.example.beanricecakemall.customDTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2Response oAuth2Response;

    private final String user_role;

    public CustomOAuth2User(OAuth2Response oAuth2Response, String userRole) {
        this.oAuth2Response = oAuth2Response;
        user_role = userRole;
    }


    @Override
    public Map<String, Object> getAttributes() {
        //로그인을 진행하면 서버로부터 넘어오는 모든 데이터
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //role값
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user_role;
            }
        });
        return null;
    }

    @Override
    public String getName() {
        //사용자의 이름이나 별명 값
        return oAuth2Response.getName();
    }

    public String getUserName(){
        return oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
    }
}
