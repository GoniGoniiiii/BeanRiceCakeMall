package com.example.beanricecakemall.config;

import com.example.beanricecakemall.jwt.JWTFilter;
import com.example.beanricecakemall.jwt.JWTUtil;
import com.example.beanricecakemall.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
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
        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                                CorsConfiguration configuration=new CorsConfiguration();

                                //허용할 프론트엔드쪽 서버에서 데이터를 보낼거기 때문에 3000번대 포트 허용
                                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                                //허용할 메소드 get,post,등등 모든 메소드 허용
                                configuration.setAllowedMethods(Collections.singletonList("*"));
                                //프론트에서 Credential 설정을 하면 true로 바꿔줘야된대
                                configuration.setAllowCredentials(true);
                                //허용할 헤더
                                configuration.setAllowedHeaders(Collections.singletonList("*"));
                                //시간
                                configuration.setMaxAge(3600L);

                                //백에서 사용자 클라이언트단으로 header를 보내줄때 authorization에 jwt를 넣어서 보내줄거기때문에  authorization header도 허용을 시켜줘야함
                                configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                                return configuration;
                            }
                        }));

        //csrf.disable()
        //session방식은 csrf 공격에 방어해야하지만 jwt방식은 session이 stateLess이기 때문에 csrf 공격에 필수로 방어할 필요는 없음.
        http
                .csrf((auth) -> auth.disable());

        //formLogin disable
        http
                .formLogin((auth)-> auth.disable());

        //httpBasic disable
        http
                .httpBasic((auth)->auth.disable());

        http
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/css/**", "/img/**", "/js/**", "/fullcalendar-6.1.5/**","/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);


        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);


        http
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

}