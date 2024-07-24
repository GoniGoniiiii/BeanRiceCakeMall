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
        return "redirect: product/productList";
    }

    @GetMapping("/productList")
    public String productList(Model model){
        List<ProductDTO> productList=productService.productDTOList();
        model.addAttribute("productList",productList);
        return "product/productList";
    }
}
