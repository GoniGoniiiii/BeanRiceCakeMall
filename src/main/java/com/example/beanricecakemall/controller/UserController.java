package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.CustomUserDetailService;
import com.example.beanricecakemall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ResponseBody
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/user/join")
    public String join(UserDTO userDTO){
        System.out.println(userDTO);
        userService.join(userDTO);
        return "user/login";
    }


}
