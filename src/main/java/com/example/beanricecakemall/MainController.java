package com.example.beanricecakemall;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {


    @GetMapping("/payment")
    public String paymentP(){
        return "product/payment";
    }
    
    @GetMapping("/login")
    public String loingP(){
        return "user/login";
    }

    @GetMapping("/admin")
    public String admin(){return "user/login";}

    @GetMapping("/productDetail")
    public String productDeatilP(){
        return "product/productDetail";
    }

    @GetMapping("/my/shoppingBag")
    public String shoppingBag(){ return "product/shoppingBag";}

    @GetMapping("/my/orderList")
    public String orderListP(){return "user/orderList";}

    @GetMapping("/orderListDetail")
    public String orderListDetailP(){return "user/orderListDetail";}

    @GetMapping("/user/join")
    public String joinP(){ return "user/join";}

    @GetMapping("/my/memberInfo")
    public String memberInfoP(){return "user/memberInfo";}
    
    @GetMapping("/user/findId")
    public String findId(){return "user/findId";}

    @GetMapping("/user/findPw")
    public String findPw(){return "user/findPw";}

    @GetMapping("/paymentFailed")
    public String paymentFailedP(){ return "product/paymentFailed";}

    @GetMapping("/paymentCompleted")
    public String paymentCompletedP(){ return "product/paymentCompleted";}

    @GetMapping("/admin/main")
    public String adminMainP(){return "admin/adminMain";}

    @GetMapping("/admin/uploadProduct")
    public String uploadProductP(){return "admin/uploadProduct";}

    @GetMapping("/")
    public String mainP(Model model){
        String id= SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator=authorities.iterator();

        String role=null;

        if(iterator.hasNext()){
            GrantedAuthority auth=iterator.next();
            role=auth.getAuthority();
        }else{
            role="no_role";
        }

         model.addAttribute("id",id);
         model.addAttribute("role",role);
        System.out.println("main controller :  " +  id +" "+  role);
        return "index";
    }
}

