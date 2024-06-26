package com.example.beanricecakemall.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf.disable()
        //session방식은 csrf 공격에 방어해야하지만 jwt방식은 session이 stateLess이기 때문에 csrf 공격에 필수로 방어할 필요는 없음.
        http
                .csrf((auth) -> auth.disable());

        //formLogin disable
        http
                .formLogin((auth)->auth.disable());

        //httpBasic disable
        http
                .httpBasic((auth)->auth.disable());

        http
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/css/**", "/img/**", "/js/**", "/fullcalendar-6.1.5/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated());

//
//        http
//                .addFilterAt()

        http
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

}