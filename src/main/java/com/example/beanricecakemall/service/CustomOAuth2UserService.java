package com.example.beanricecakemall.service;

import com.example.beanricecakemall.customDTO.CustomOAuth2User;
import com.example.beanricecakemall.customDTO.GoogleResponse;
import com.example.beanricecakemall.customDTO.NaverResponse;
import com.example.beanricecakemall.customDTO.OAuth2Response;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //super를 통해서 부모클래스에 존재하는 loadUser 메소드에서 userRequest를 넣어 user정보 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);

        //가져온 정보 확인
        System.out.println(oAuth2User.getAttributes());

        //OAuth2User로 구글, 네이버 다 넘어오기 때문에 구글 or 네이버 인지 확인
        String registration = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        //굳이 둘을 나누는 이유 ? 인증 규격이 달라서 서로 다른 dto바구니에 담아줘야 하기 때문
        if (registration.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registration.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        //받아온 정보를 db에 저장, 업데이트 해주면 된다~~
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        UserEntity exist = userRepository.findByUserId(username);

        String user_role = null;

        if (exist == null) {
            UserEntity user = new UserEntity();
            user.setUserId(username);
            user.setUserEmail(oAuth2Response.getEmail());
            user.setUserName(oAuth2Response.getName());
            user.setUserRole("ROLE_USER");
            user.setUserRegistration("N");

            if (username.split(" ")[0].equals("naver")) {
                user.setUserBirth(oAuth2Response.getBirthYear() + "-" + oAuth2Response.getBirthDay());
                user.setUserTel(oAuth2Response.getPhone());
            }
            userRepository.save(user);

        } else {
            user_role = exist.getUserRole();
            exist.setUserEmail(oAuth2Response.getEmail());
            userRepository.save(exist);
        }
        return new CustomOAuth2User(oAuth2Response, user_role);
    }
}
