package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.UserService;
import jakarta.servlet.http.Cookie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String memberUpdate(@ModelAttribute UserDTO userDTO) {
        System.out.println("마이페이지 - 회원 정보 수정");
        userService.update(userDTO);
        return "redirect:/my/memberInfo";
    }

    @GetMapping("/user/unregister/{user_num}")
    public String unregister(@PathVariable int user_num) {
        System.out.println("user_num : " + user_num);
        System.out.println("회원 탈퇴");
        userService.delete(user_num);
        return "redirect:/";
    }

    @PostMapping("/user/findId")
    public ResponseEntity<String> findId(@RequestBody UserDTO userDTO) {
        System.out.println("아이디 찾기");
        System.out.println(userDTO.getUser_name() + userDTO.getUser_email() + userDTO.getUser_tel());
        String user_id = userService.findId(userDTO);
        System.out.println(user_id);
        return ResponseEntity.ok(user_id);
    }

    @PostMapping("/user/findPw")
    public ResponseEntity<String> findPw(@RequestBody UserDTO userDTO){
        System.out.println("비밀번호 찾기");
        System.out.println(userDTO.getUser_name() + userDTO.getUser_email() + userDTO.getUser_tel()+userDTO.getUser_id());
        String user_pw=userService.findPw(userDTO);
        System.out.println(user_pw);
        return ResponseEntity.ok(user_pw);
    }
}