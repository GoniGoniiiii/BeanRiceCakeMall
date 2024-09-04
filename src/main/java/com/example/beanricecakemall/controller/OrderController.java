package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.*;
import com.example.beanricecakemall.entity.OrderProductEntity;
import com.example.beanricecakemall.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    private final CartService cartService;

    private final OrderProductService orderProductService;

    private final DeliveryService deliveryService;

    private final ReviewService reviewService;

    public OrderController(UserService userService, ProductService productService, OrderService orderService, CartService cartService, OrderProductService orderProductService, DeliveryService deliveryService, ReviewService reviewService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.orderProductService = orderProductService;
        this.deliveryService = deliveryService;
        this.reviewService = reviewService;
    }


    @PostMapping("/pay")
    public ResponseEntity<String> paymentP(@RequestBody CartDTO cartDTO, HttpSession session) {
        //cartList에서 user_num 뽑아오기(user_num은 어차피 하나라서 아무거나 뽑아오면 됨)
        System.out.println("cartDTO : " + cartDTO);
        int user_num = cartDTO.getUser_num();
        System.out.println("뽑아온 user_num: " + user_num);

        UserDTO userDTO = userService.findUserInfo(user_num);
        System.out.println(userDTO.toString());

        int product_num = cartDTO.getProduct_num();

        //뽑아온 product_num이용해서 필요한 정보들 가져오기
        ProductDTO productDTO = productService.findProductInfo(product_num);

        session.setAttribute("cart", cartDTO);
        session.setAttribute("user", userDTO);
        session.setAttribute("product", productDTO);
        return ResponseEntity.ok("뿡");
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

    @GetMapping("/payment2") //바로구매 했을때 ( 장바구니 거치지 않음)
    public String getPaymentPage2(HttpSession session, Model model) {
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        ProductDTO productDTO = (ProductDTO) session.getAttribute("product");

        int total_oprice = 0;
        int total_sale = 0;
        int total_delivery = 0;
        int total_price = 0;
        int order_cnt = 0;


        total_oprice = cart.getTotal_oprice();
        total_sale = cart.getTotal_sale();
        System.out.println("총 세일값 : " + total_sale);

        total_delivery = cart.getTotal_delivery();
        System.out.println("총 배달료 : " + total_delivery);

        total_price = cart.getTotal_price();
        System.out.println("총 결제금액 : " + total_price);

        order_cnt = cart.getCart_cnt();
        System.out.println("주문 수량 : " + order_cnt);

        model.addAttribute("total_oprice", total_oprice);
        model.addAttribute("total_sale", total_sale);
        model.addAttribute("total_delivery", total_delivery);
        model.addAttribute("total_price", total_price);
        model.addAttribute("order_cnt", order_cnt);
        model.addAttribute("user", userDTO);
        model.addAttribute("product", productDTO);

        return "product/payment";
    }


    @GetMapping("/payment") //장바구니 거쳐서 구매할때
    public String getPaymentPage(HttpSession session, Model model) {
        List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cart");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<ProductDTO> productDTOList = (List<ProductDTO>) session.getAttribute("product");
        System.out.println(cartList.toString());

        int total_oprice = 0;
        int total_sale = 0;
        int total_delivery = 0;
        int total_price = 0;
        List<Integer> order_cnt = new ArrayList<>();

        for (CartDTO cart : cartList) {
            total_oprice = cart.getTotal_oprice();
            System.out.println("총 원가 : " + total_oprice);

            total_sale = cart.getTotal_sale();
            System.out.println("총 세일값 : " + total_sale);

            total_delivery = cart.getTotal_delivery();
            System.out.println("총 배달료 : " + total_delivery);

            total_price = cart.getTotal_price();
            System.out.println("총 결제금액 : " + total_price);

            order_cnt.add(cart.getCart_cnt());
        }

//        model.addAttribute("cart", cartList);
        model.addAttribute("total_oprice", total_oprice);
        model.addAttribute("total_sale", total_sale);
        model.addAttribute("total_delivery", total_delivery);
        model.addAttribute("total_price", total_price);
        model.addAttribute("order_cnt", order_cnt);
        model.addAttribute("user", userDTO);
        model.addAttribute("product", productDTOList);

        return "product/payment";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute OrderDTO orderDTO, @ModelAttribute DeliveryDTO deliveryDTO) {
        System.out.println(orderDTO.toString());
        System.out.println(deliveryDTO.toString());
        int order_num = orderService.insertOrder(orderDTO, deliveryDTO);

        System.out.println("컨트롤러 product_num : " + orderDTO.getProduct_num());
        if (order_num != 0) {
            for (int product_num : orderDTO.getProduct_num()) {
                //주문이 성공하면 장바구니에서 상품 삭제
                cartService.delete(product_num, orderDTO.getUser_num());

                orderDTO.setOrder_num(order_num);
            }
            //주문 상세 테이블에 추가
            orderProductService.insertOrder(orderDTO);
            // 적립금 추가
            userService.plusPoint(orderDTO.getUser_num(), orderDTO.getPlus_point());


            return "product/paymentCompleted";
        } else {
            return "product/paymentFailed";
        }
    }

    @GetMapping("/my/orderList")
    public String orderListP(Model model) {
        //security를 이용해서 user_id가져오기
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        //가져온 user_id값으로 orderDTO뽑아주기
        List<OrderDTO> orderDTOS = orderService.orderList(user_id);

        OrderDTO orderDTO = new OrderDTO();
        List<String> product_img = new ArrayList<>();
        List<String> product_name = new ArrayList<>();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<Integer> product_num = new ArrayList<>();

        Set<Integer> processedProductNums = new HashSet<>();

        for (OrderDTO order : orderDTOS) {
            orderDTO = order;
            System.out.println(" 컨트롤러 orderDTO : " + orderDTO.toString());
            orderDTOList.add(orderDTO);
            // 마지막 주문 번호를 리스트에 추가

            //product_img,product_name 가져오기
            List<Integer> productNum = orderDTO.getProduct_num();

            for (int product : productNum) {
                if (!processedProductNums.contains(product)) {
                    // product가 중복되지 않을 경우에만 추가
                    processedProductNums.add(product);
                    product_img.add(productService.findProductImg(product));
                    product_name.add(productService.findProductName(product));
                    product_num.add(product);
                }
            }
            System.out.println(product_img.toString());
            System.out.println(product_name.toString());
        }

        // 주문 번호별로 그룹화
        Map<Integer, List<OrderDTO>> orders = orderDTOS.stream()
                .collect(Collectors.groupingBy(OrderDTO::getOrder_num));

        Map<Integer, Integer> rowspanMap = orders.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));

        // 모델에 데이터 추가
        model.addAttribute("orderDTOList", orderDTOList);
        model.addAttribute("product_num", product_num);
        model.addAttribute("product_img", product_img);
        model.addAttribute("product_name", product_name);
        model.addAttribute("ordersGroupedByOrderNum", orders);
        model.addAttribute("rowspanMap", rowspanMap);

        return "user/orderList";
    }


    @GetMapping("/my/orderListDetail/{order_num}")
    public String orderListDetailP(@PathVariable int order_num, Model model) {
        OrderDTO orderDTO = new OrderDTO();
        //주문정보  , 결제정보, 상품정보
        orderDTO = orderService.orderDetail(order_num);

        //배송지 정보
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO = deliveryService.deliveryInfo(order_num);

        System.out.println("상세 정보 orderDTO :" + orderDTO.toString());
        System.out.println("상세정보 deliveryDTO :" + deliveryDTO.toString());

        //product_img,product_name 가져오기
        List<Integer> product_num = orderDTO.getProduct_num();
        List<String> product_img = new ArrayList<>();
        List<String> product_name = new ArrayList<>();

        //주문상품정보 리스트
        List<OrderDTO> orderProductList = new ArrayList<>();
        orderProductList = orderProductService.findOrderProductList(order_num);

        for (int product : product_num) {
            product_img.add(productService.findProductImg(product));
            product_name.add(productService.findProductName(product));
            System.out.println(" product : " + product);

        }
        System.out.println(product_img.toString());
        System.out.println(product_name.toString());


        //model 객체에 담아서 넘겨주기
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("orderProductList", orderProductList);
        model.addAttribute("deliveryDTO", deliveryDTO);
        model.addAttribute("product_img", product_img);
        model.addAttribute("product_name", product_name);
        return "user/orderListDetail";
    }

    @PostMapping("/my/addReview")
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO){
        System.out.println("reviewDTO : " + reviewDTO);
        String result=reviewService.insertReview(reviewDTO);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/my/updateReview")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewDTO reviewDTO){
        System.out.println("reviewDTO  : " +reviewDTO);
        reviewService.updateReview(reviewDTO);
        return ResponseEntity.ok().build();
    }


    
}
