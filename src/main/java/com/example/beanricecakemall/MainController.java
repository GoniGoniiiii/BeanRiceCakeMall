package com.example.beanricecakemall;

import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {
    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/login")
    public String loingP() {
        return "user/login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "user/login";
    }

    @GetMapping("/user/join")
    public String joinP() {
        return "user/join";
    }

    @GetMapping("/user/findId")
    public String findId() {
        return "user/findId";
    }

    @GetMapping("/user/findPw")
    public String findPw() {
        return "user/findPw";
    }

    @GetMapping("/paymentFailed")
    public String paymentFailedP() {
        return "product/paymentFailed";
    }

    @GetMapping("/paymentCompleted")
    public String paymentCompletedP() {
        return "product/paymentCompleted";
    }

    @GetMapping("/admin/main")
    public String adminMainP() {
        return "admin/adminMain";
    }

    @GetMapping("/admin/uploadProduct")
    public String uploadProductP() {
        return "admin/uploadProduct";
    }

    @GetMapping("/")
    public String mainP(Model model, @RequestParam(required = false) String sort, HttpServletRequest request) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        //메인페이지 상품 불러오기
        //인기상품
        List<ProductDTO> bestProduct = productService.productDTOList(11);
        List<List<ProductDTO>> bestProductList = new ArrayList<>();
        for (int i = 0; i < bestProduct.size(); i += 4) {
            bestProductList.add(bestProduct.subList(i, Math.min(i + 4, bestProduct.size())));
        }

        //신상품
        List<ProductDTO> newProduct = productService.productDTOList(12);
        List<List<ProductDTO>> newProductList = new ArrayList<>();
        for (int i = 0; i < newProduct.size(); i += 4) {
            newProductList.add(newProduct.subList(i, Math.min(i + 4, newProduct.size())));
        }

        //전체상품
        List<ProductDTO> product;

        if (sort != null) {
            // 필터 정렬이 적용된 경우
            product = productService.sortProductList(13, sort);
        } else {
            // 정렬되지 않은 경우
            product = productService.productDTOList(13);
        }

        // 5개씩 묶기
        List<List<ProductDTO>> allProductList = new ArrayList<>();
        for (int i = 0; i < product.size(); i += 5) {
            allProductList.add(product.subList(i, Math.min(i + 5, product.size())));
        }

        String role = null;

        if (iterator.hasNext()) {
            GrantedAuthority auth = iterator.next();
            role = auth.getAuthority();
        }else{
            role = "no_role";
        }

        model.addAttribute("id", id);
        model.addAttribute("role", role);
        model.addAttribute("best", bestProductList);
        model.addAttribute("newPro", newProductList);
        model.addAttribute("all", allProductList);
        System.out.println("main controller :  " + id + " " + role);

        // AJAX 요청일 경우, 상품 리스트 HTML만 반환
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "index :: allProductList"; // 특정 부분만 렌더링
        }
        return "index";
    }
}

