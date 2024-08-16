package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.*;
import com.example.beanricecakemall.service.CartService;
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

    private final CartService cartService;

    public OrderController(UserService userService, ProductService productService, OrderService orderService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.cartService = cartService;
    }


    @PostMapping("/my/payment")
    public ResponseEntity<String> paymentP(@RequestBody List<CartDTO> cartList, HttpSession session) {
        //cartList에서 user_num 뽑아오기(user_num은 어차피 하나라서 아무거나 뽑아오면 됨)
        int user_num = cartList.get(0).getUser_num();
        System.out.println("뽑아온 user_num: " + user_num);

        UserDTO userDTO = userService.findUserInfo(user_num);
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
            System.out.println("뽑아낸 productDTO :" + productDTO.toString());
            if (productDTO != null) {
                productDTOList.add(productDTO);
            }
        }

        session.setAttribute("cart", cartList);
        session.setAttribute("user", userDTO);
        session.setAttribute("product", productDTOList);
        return ResponseEntity.ok("뿡");
    }


    @GetMapping("/payment")
    public String getPaymentPage(HttpSession session, Model model) {
        List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cart");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<ProductDTO> productDTOList = (List<ProductDTO>) session.getAttribute("product");
        System.out.println(cartList.toString());

        int total_oprice = 0;
        int total_sale = 0;
        int total_delivery = 0;
        int total_price=0;

        for (CartDTO cart : cartList) {
            total_oprice=cart.getTotal_oprice();
            System.out.println("총 원가 : " + total_oprice);
            total_sale=cart.getTotal_sale();
            System.out.println("총 세일값 : "+total_sale);
            total_delivery=cart.getTotal_delivery();
            System.out.println("총 배달료 : "+total_delivery);
            total_price=cart.getTotal_price();
            System.out.println("총 결제금액 : "+total_price);

        }

//        model.addAttribute("cart", cartList);
        model.addAttribute("total_oprice",total_oprice);
        model.addAttribute("total_sale",total_sale);
        model.addAttribute("total_delivery",total_delivery);
        model.addAttribute("total_price",total_price);
        model.addAttribute("user", userDTO);
        model.addAttribute("product", productDTOList);

        return "product/payment"; // Thymeleaf 템플릿 이름
    }

    @PostMapping("/order")
    public String order(@ModelAttribute OrderDTO orderDTO, @ModelAttribute DeliveryDTO deliveryDTO) {
        System.out.println(orderDTO.toString());
        System.out.println(deliveryDTO.toString());
        boolean order=orderService.insertOrder(orderDTO,deliveryDTO);
        System.out.println("컨트롤러 product_num : " + orderDTO.getProduct_num());
        if(order){
            for(int product_num:orderDTO.getProduct_num()) {
                cartService.delete(product_num, orderDTO.getUser_num());
            }
            return "product/paymentCompleted";
        }else{
            return "product/paymentFailed";
        }
    }
}
