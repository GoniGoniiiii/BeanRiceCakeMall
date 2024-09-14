package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.customDTO.CustomUserDetails;
import com.example.beanricecakemall.dto.CategoryDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.dto.ReviewDTO;
import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.service.CategoryService;
import com.example.beanricecakemall.service.CustomUserDetailsService;
import com.example.beanricecakemall.service.ProductService;
import com.example.beanricecakemall.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final ReviewService reviewService;

    public ProductController(ProductService productService, CategoryService categoryService, ReviewService reviewService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.reviewService = reviewService;
    }

    @PostMapping("/admin/uploadProduct")
    public String upload(@ModelAttribute ProductDTO productDTO) {
        System.out.println(productDTO);
        productService.upload(productDTO);
        return "redirect:/productList/13";
    }

    @GetMapping("/productList/{category_num}")
    public String productList(@PathVariable int category_num, @RequestParam(required = false) String sort, Model model, HttpServletRequest request) {
        List<ProductDTO> product;
        String category = categoryService.categoryName(category_num);

        if (sort != null) {
            // 필터 정렬이 적용된 경우
            product = productService.sortProductList(category_num, sort);
        } else {
            // 정렬되지 않은 경우
            product = productService.productDTOList(category_num);
        }

        // 5개씩 묶기
        List<List<ProductDTO>> productList = new ArrayList<>();
        for (int i = 0; i < product.size(); i += 5) {
            productList.add(product.subList(i, Math.min(i + 5, product.size())));
        }

        model.addAttribute("category_num", category_num);
        model.addAttribute("category", category);
        model.addAttribute("productList", productList);

        // AJAX 요청일 경우, 상품 리스트 HTML만 반환
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "product/productList :: productList"; // 특정 부분만 렌더링
        }

        return "product/productList";
    }


    @GetMapping("/productDetail/{product_num}")
    public String productDetail(@PathVariable int product_num, Model model) {
        ProductDTO product = productService.productDetail(product_num);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ReviewDTO> reviewList = reviewService.ListReview(product_num);
        List<ReviewDTO> recentReviewList = reviewService.RecentListReview(product_num);

        System.out.println(reviewList.toString());
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            int userNum = userDetails.getUserNum();
            System.out.println(userNum);
            id = userDetails.getUsername();

            model.addAttribute("product", product);
            model.addAttribute("user_id", id);
            model.addAttribute("user_num", userNum);
            model.addAttribute("recent", recentReviewList);
            model.addAttribute("review", reviewList);
        } else {
            // principal이 CustomUserDetails가 아닌 경우의 처리
            model.addAttribute("product", product);
            model.addAttribute("user_id", id);
            model.addAttribute("user_num", -1);
            model.addAttribute("recent", recentReviewList);
            model.addAttribute("review", reviewList);
        }
        return "product/productDetail";
    }

    @GetMapping("/admin/updateProduct/{product_num}")
    public String updateP(@PathVariable int product_num, Model model) {
        System.out.println("상품 수정 페이지");
        ProductDTO productDTO = productService.productDetail(product_num);
        model.addAttribute("product", productDTO);
        return "admin/updateProduct";
    }

    @PostMapping("/admin/updateProduct")
    public String updateProduct(@ModelAttribute ProductDTO productDTO, Model model) {
        System.out.println("상품 수정 중");
        int product_num = productDTO.getProduct_num();
        System.out.println(product_num);
        ProductDTO updateProduct = productService.updateProduct(productDTO);
        model.addAttribute("product", updateProduct);
        return "redirect:/productDetail/" + product_num;
    }

    @PostMapping("/img/delete/{file_url}")
    public ResponseEntity<String> imgDelete(@PathVariable String file_url) {
        productService.imgDelete(file_url);
        System.out.println(file_url + "이미지 삭제 완료 ");
        return ResponseEntity.ok("이미지 삭제 완료");
    }

    @GetMapping("/product/deleteProduct/{product_num}")
    public String deleteProudct(@PathVariable int product_num) {
        //상품 삭제
        productService.deleteProduct(product_num);
        return "redirect:/productList/13";
    }

    //검색
    @GetMapping("/product/search")
    public String search(String keyword, Model model) {
        List<ProductDTO> searchList = new ArrayList<>();
        String category = "검색";

        if (keyword != null && !keyword.trim().isEmpty()) {
            // 키워드가 있을 경우에만 검색 실행
            searchList = productService.search(keyword);
        }
        // 5개씩 묶기
        List<List<ProductDTO>> productList = new ArrayList<>();
        for (int i = 0; i < searchList.size(); i += 5) {
            productList.add(searchList.subList(i, Math.min(i + 5, searchList.size())));
        }

        model.addAttribute("productList", productList);
        model.addAttribute("category", category);
        return "product/productList";
    }
}
