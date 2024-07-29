package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.CategoryDTO;
import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.service.CategoryService;
import com.example.beanricecakemall.service.ProductService;
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
        String category=categoryService.categoryName(category_num);
        // 5개씩 묶기
        List<List<ProductDTO>> productList = new ArrayList<>();
        for (int i = 0; i < product.size(); i += 5) {
            productList.add(product.subList(i, Math.min(i + 5, product.size())));
        }
        model.addAttribute("productList", productList);
        model.addAttribute("category",category);
        System.out.println(productList.toString());
        return "product/productList";
    }

    @GetMapping("/productDetail/{product_num}")
    public String productDetail(@PathVariable int product_num, Model model){
        ProductDTO product=productService.productDetail(product_num);
        model.addAttribute("product",product);
        return "product/productDetail";
    }
}
