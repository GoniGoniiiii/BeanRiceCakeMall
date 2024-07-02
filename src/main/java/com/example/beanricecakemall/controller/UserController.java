package com.example.beanricecakemall.controller;

import ch.qos.logback.core.model.Model;
import com.example.beanricecakemall.dto.CustomUserDetails;
import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.CustomUserDeatilService;
import com.example.beanricecakemall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@ResponseBody
@RequiredArgsConstructor

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDeatilService  customUserDeatilService;

    @PostMapping("/user/join")
    public String join(UserDTO userDTO){
        System.out.println(userDTO);
        userService.join(userDTO);
        return "user/login";
    }

    @PostMapping("/user/login")
    public String login(String user_id){
        System.out.println(user_id);
        customUserDeatilService.loadUserByUsername(user_id);
        return "index";
    }

}
