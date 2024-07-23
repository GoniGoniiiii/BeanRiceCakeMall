package com.example.beanricecakemall.controller;

import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;

    public  ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/admin/uploadProduct")
    public String upload(@ModelAttribute  ProductDTO productDTO) throws IOException {
        System.out.println(productDTO);
        productService.upload(productDTO);
        return "/product/productList";
    }

}
