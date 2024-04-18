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
}
