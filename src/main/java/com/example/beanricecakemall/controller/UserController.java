package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@ResponseBody
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/join")
    public String join(UserDTO userDTO){
        System.out.println("회원가입 컨트롤러");
        System.out.println(userDTO.toString());
        userService.join(userDTO);
        return "redirect:/login";
    }

//    @PostMapping("/user/login")
//    public String login(UserDTO userDTO, Model model){
//        System.out.println("로그인");
//        userService.loadUserByUsername(userDTO.getUser_id());
//        model.addAttribute("user",userDTO);
//        return "redirect:/";
//    }

}
