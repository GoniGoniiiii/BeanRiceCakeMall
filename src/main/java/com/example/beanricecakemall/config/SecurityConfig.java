package com.example.beanricecakemall.config;

import com.example.beanricecakemall.oauth2.CustomClientRegistrationRepository;
import com.example.beanricecakemall.service.CustomOAuth2UserService;
import com.example.beanricecakemall.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomClientRegistrationRepository customClientRegistrationRepository;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomClientRegistrationRepository customClientRegistrationRepository) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customClientRegistrationRepository = customClientRegistrationRepository;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        //사용자 비밀번호 해시화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((auth)->auth.disable());

        http.formLogin((auth)->auth.loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/")
                .permitAll()
        );

        http
                .oauth2Login((oauth2)-> oauth2
                        .loginPage("/login")
                        .clientRegistrationRepository(customClientRegistrationRepository.clientRegistrationRepository())
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)));

        http
                .logout((auth)->auth.
                        logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .permitAll());



        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/upload/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasRole("USER")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) //하나의 아이디에 대한 다중 로그인 허용 개수
                        .maxSessionsPreventsLogin(true) //다중 로그인 개수를 초과하였을때 어떻게 할지
                );

        http
                .sessionManagement((auth)->auth
                        .sessionFixation().changeSessionId());

        return http.build();
    }
}