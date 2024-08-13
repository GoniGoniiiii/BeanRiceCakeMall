package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.dto.UserDTO;
import com.example.beanricecakemall.service.OrderService;
import com.example.beanricecakemall.service.ProductService;
import com.example.beanricecakemall.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    public OrderController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }


    @PostMapping("/my/payment")
    public ResponseEntity<String> paymentP(@RequestBody List<CartDTO> cartList, HttpSession session) {
        //cartList에서 user_num 뽑아오기(user_num은 어차피 하나라서 아무거나 뽑아오면 됨)
        int user_num=cartList.get(0).getUser_num();
        System.out.println("뽑아온 user_num: " +  user_num);

        UserDTO userDTO=userService.findUserInfo(user_num);
        System.out.println(userDTO.toString());

        //cartList에서 productNum뽑아오기
        List<Integer> productNums = new ArrayList<>();

        for (CartDTO cartItem : cartList) {
            Integer productNum = cartItem.getProduct_num();
            if (productNum != null) {
                productNums.add(productNum);
            }
        }
    
        //뽑아온 product_num이용해서 필요한 정보들 가져오기
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Integer productNum : productNums) {
            ProductDTO productDTO = productService.findProductInfo(productNum);
            System.out.println("뽑아낸 productDTO :"+productDTO.toString());
            if (productDTO != null) {
                productDTOList.add(productDTO);
            }
        }

        session.setAttribute("cart",cartList);
        session.setAttribute("user",userDTO);
        session.setAttribute("product",productDTOList);
        return ResponseEntity.ok("뿡");
    }



    @GetMapping("/payment")
    public String getPaymentPage(HttpSession session, Model model) {
        List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cart");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<ProductDTO> productDTOList = (List<ProductDTO>) session.getAttribute("product");

        model.addAttribute("cart", cartList);
        model.addAttribute("user", userDTO);
        model.addAttribute("product", productDTOList);

        return "product/payment"; // Thymeleaf 템플릿 이름
    }
}
