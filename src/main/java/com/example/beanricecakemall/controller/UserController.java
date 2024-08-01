package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.UserService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@ResponseBody
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/join")
    public String join(UserDTO userDTO) {
        System.out.println("회원가입 컨트롤러");
        System.out.println(userDTO.toString());
        userService.join(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/my/memberInfo")
    public String memberInfo(Model model) {
        System.out.println("마이페이지 - 회원 정보 수정");
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(user_id);
        UserDTO member = userService.memberInfo(user_id);
        model.addAttribute("member", member);
        return "user/memberInfo";
    }

    @PostMapping("/my/memberInfo")
    public String memberUpdate(@ModelAttribute UserDTO userDTO){
        System.out.println("마이페이지 - 회원 정보 수정");
        userService.update(userDTO);
        return "redirect:/my/memberInfo";
    }

    @GetMapping("/user/unregister/{user_num}")
    public String unregister(@PathVariable int user_num){
        System.out.println("user_num : " + user_num);
        System.out.println("회원 탈퇴");
        userService.delete(user_num);
        return "redirect:/";
    }
}
