package com.example.beanricecakemall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/productList")
    public String productListP(){
        return "product/productList";

    }
    @GetMapping("/payment")
    public String paymentP(){
        return "product/payment";
    }
    @GetMapping("/login")
    public String loingP(){
        return "user/login";
    }

    @GetMapping("/productDetail")
    public String productDeatilP(){
        return "product/productDetail";
    }

    @GetMapping("/shoppingBag")
    public String shoppingBag(){ return "product/shoppingBag";}

    @GetMapping("/orderList")
    public String orderListP(){return "user/orderList";}

    @GetMapping("/orderListDetail")
    public String orderListDetailP(){return "user/orderListDetail";}

    @GetMapping("/join")
    public String joinP(){ return "user/join";}

    @GetMapping("/memberInfo")
    public String memberInfoP(){return "user/memberInfo";}
    
    @GetMapping("/findId")
    public String findId(){return "user/findId";}

    @GetMapping("/findPw")
    public String findPw(){return "user/findPw";}


}

