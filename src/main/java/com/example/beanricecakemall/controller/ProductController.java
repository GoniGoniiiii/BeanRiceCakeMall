package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public  ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/admin/uploadProduct")
    public String upload(@ModelAttribute  ProductDTO productDTO){
        System.out.println(productDTO);
        productService.upload(productDTO);
        return "redirect:/productList";
    }

    @GetMapping("/productList")
    public String productList(Model model){
        List<ProductDTO> product=productService.productDTOList();
     // 5개씩 묶기
        List<List<ProductDTO>> productList = new ArrayList<>();
        for (int i = 0; i < product.size(); i += 5) {
            productList.add(product.subList(i, Math.min(i + 5, product.size())));
        }
        model.addAttribute("productList", productList);
        System.out.println(productList.toString());
        return "product/productList";
    }

}