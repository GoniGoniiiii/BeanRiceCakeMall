package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/{user_num}")
    public String cartP(@PathVariable int user_num, Model model) {
        return "product/shoppingBag";
    }

    @PostMapping("/cart")
    public ResponseEntity<String> cart(@RequestBody CartDTO cartDTO) {
        System.out.println("cartDTO" +cartDTO);
        String result= cartService.insert(cartDTO);
        return ResponseEntity.ok(result);
    }
}
