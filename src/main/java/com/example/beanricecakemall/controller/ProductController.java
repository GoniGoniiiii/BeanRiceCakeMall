package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.customDTO.CustomUserDetails;
import com.example.beanricecakemall.dto.CategoryDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.service.CategoryService;
import com.example.beanricecakemall.service.CustomUserDetailsService;
import com.example.beanricecakemall.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/uploadProduct")
    public String upload(@ModelAttribute ProductDTO productDTO) {
        System.out.println(productDTO);
        productService.upload(productDTO);
        return "redirect:/productList/13";
    }

    @GetMapping("/productList/{category_num}")
    public String productList(@PathVariable int category_num, Model model) {
        List<ProductDTO> product = productService.productDTOList(category_num);
        String category = categoryService.categoryName(category_num);

        // 5개씩 묶기
        List<List<ProductDTO>> productList = new ArrayList<>();
        for (int i = 0; i < product.size(); i += 5) {
            productList.add(product.subList(i, Math.min(i + 5, product.size())));
        }
        model.addAttribute("productList", productList);
        model.addAttribute("category", category);
        System.out.println(productList.toString());
        return "product/productList";
    }

    @GetMapping("/productDetail/{product_num}")
    public String productDetail(@PathVariable int product_num, Model model) {
        ProductDTO product = productService.productDetail(product_num);
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=SecurityContextHolder.getContext().getAuthentication().getName();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            int userNum = userDetails.getUserNum();
            System.out.println(userNum);
            id = userDetails.getUsername();

            model.addAttribute("product", product);
            model.addAttribute("user_id", id);
            model.addAttribute("user_num", userNum);
        } else {
            // principal이 CustomUserDetails가 아닌 경우의 처리
            model.addAttribute("product", product);
            model.addAttribute("user_id", id);
            model.addAttribute("user_num", -1);
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
        int product_num=productDTO.getProduct_num();
        System.out.println(product_num);
        ProductDTO updateProduct = productService.updateProduct(productDTO);
        model.addAttribute("product",updateProduct);
        return "redirect:/productDetail/"+product_num;
    }

    @PostMapping("/img/delete/{file_url}")
    public ResponseEntity<String> imgDelete(@PathVariable String file_url) {
        productService.imgDelete(file_url);
        System.out.println(file_url + "이미지 삭제 완료 ");
        return ResponseEntity.ok("이미지 삭제 완료");
    }

    @GetMapping("/product/deleteProduct/{product_num}")
    public String deleteProudct(@PathVariable int product_num){
        //상품 삭제
        productService.deleteProduct(product_num);
        return "redirect:/productList/13";
    }
}
