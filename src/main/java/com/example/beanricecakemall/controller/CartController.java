package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.service.CartService;
import com.example.beanricecakemall.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }


    @PostMapping("/cart")
    public ResponseEntity<String> cart(@RequestBody CartDTO cartDTO) {
        System.out.println("장바구니에 전달" );
        String result= cartService.insert(cartDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/my/shoppingBag/{user_num}")
    public String cartP(@PathVariable int user_num, Model model) {
        System.out.println("장바구니 내용 출력");
        
        //장바구니 리스트 얻어오기
        List<CartDTO> cartDTOList=cartService.cartList(user_num);
        //product_name 가져오기
        List<String> product_name=new ArrayList<>();
        List<Integer> product_sprice=new ArrayList<>();
            for (CartDTO cartDTO : cartDTOList) {
                String product= productService.findProductName(cartDTO.getProduct_num());
                product_name.add(product);
                int price= productService.findProductSprice(cartDTO.getProduct_num());
                product_sprice.add(price);
            }
        System.out.println(product_name);
        model.addAttribute("cart",cartDTOList);
        model.addAttribute("product_name",product_name);
        model.addAttribute("product_sprice",product_sprice);
        return "product/shoppingBag";
    }

}
