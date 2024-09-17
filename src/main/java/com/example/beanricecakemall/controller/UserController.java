package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.customDTO.CustomOAuth2User;
import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.websocket.Session;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@ResponseBody
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/check-id")
    public ResponseEntity<Boolean> checkId (@RequestBody UserDTO userDTO){
        System.out.println("받아온 user_id : "  +userDTO.getUser_id());
        boolean check=userService.exist(userDTO.getUser_id());
        return ResponseEntity.ok(check);
    }

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
        String user_id;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOauth2User=authentication.getPrincipal() instanceof OAuth2User;
        System.out.println("소셜로그인 여부 : " + isOauth2User);

        if(isOauth2User){
            CustomOAuth2User customOAuth2User=(CustomOAuth2User) authentication.getPrincipal();
            System.out.println("userName : " + customOAuth2User.getUserName());
            user_id=customOAuth2User.getUserName();
        }else{
            user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        System.out.println("user_id : " + user_id);
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