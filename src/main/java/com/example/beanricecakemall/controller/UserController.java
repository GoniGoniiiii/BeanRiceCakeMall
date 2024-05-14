package com.example.beanricecakemall.controller;

import ch.qos.logback.core.model.Model;
import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ResponseBody
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/join")
    public String join(UserDTO userDTO){
        System.out.println(userDTO);
        userService.join(userDTO);
        return "user/login";
    }
}
