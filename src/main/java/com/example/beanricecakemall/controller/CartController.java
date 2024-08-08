package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.service.CartService;
import com.example.beanricecakemall.service.ProductService;
import com.example.beanricecakemall.service.UserService;
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

    private final UserService userService;

    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }


    @PostMapping("/cart")
    public ResponseEntity<String> cart(@RequestBody CartDTO cartDTO) {
        System.out.println("장바구니에 전달" );
        String result= cartService.insert(cartDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping ("/my/shopping/{user_id}")
    public String myPageCart(@PathVariable String user_id){
        System.out.println("user_id" +  user_id);

        int user_num=userService.findUserNum(user_id);
        return  "redirect:/my/shoppingBag/"+user_num;
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
        System.out.println("cart:"+cartDTOList);
        model.addAttribute("cart",cartDTOList);
        model.addAttribute("product_name",product_name);
        model.addAttribute("product_sprice",product_sprice);
        return "product/shoppingBag";
    }

    @DeleteMapping("/cart/delete")
    public ResponseEntity<Void> deleteCart(@RequestBody CartDTO cartDTO){
        cartService.delete(cartDTO.getProduct_num(),cartDTO.getUser_num());
        return ResponseEntity.ok().build();
    }
}
