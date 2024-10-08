package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.customDTO.CustomOAuth2User;
import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.service.CartService;
import com.example.beanricecakemall.service.ProductService;
import com.example.beanricecakemall.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/my/shoppingBag")
    public String cartP(Model model) {
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

        if ("anonymousUser".equals(user_id)) {
            // 비로그인 상태일 때 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        //user_id값으로 user_num찾아와서 장바구니 리스트 뿌리기
        int user_num = userService.findUserNum(user_id);
        List<CartDTO> cartDTOList = cartService.cartList(user_num);
        List<String> product_name = new ArrayList<>();
        List<Integer> product_sprice = new ArrayList<>();
        List<Integer> product_deliveryFee=new ArrayList<>();
        List<Integer> product_oprice=new ArrayList<>();

        for (CartDTO cartDTO : cartDTOList) {
            String product = productService.findProductName(cartDTO.getProduct_num());
            product_name.add(product);
            int price = productService.findProductSprice(cartDTO.getProduct_num());
            product_sprice.add(price);
            int deliveryFee=productService.findProductDeliveryFee(cartDTO.getProduct_num());
            product_deliveryFee.add(deliveryFee);
            int oPrice=productService.findProductOprice(cartDTO.getProduct_num());
            product_oprice.add(oPrice);
        }

        model.addAttribute("cart", cartDTOList);
        model.addAttribute("product_name", product_name);
        model.addAttribute("product_sprice", product_sprice);
        model.addAttribute("product_oprice",product_oprice);
        model.addAttribute("product_deliveryFee",product_deliveryFee);
        return "product/shoppingBag";
    }

    @PostMapping("/cart/update")
    public ResponseEntity<Void> updateCart(@RequestBody CartDTO cartDTO){
        cartService.update(cartDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cart/delete")
    public ResponseEntity<Void> deleteCart(@RequestBody CartDTO cartDTO){
        cartService.delete(cartDTO.getProduct_num(),cartDTO.getUser_num());
        return ResponseEntity.ok().build();
    }
}
