package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {

    private int product_num;

    private String product_name;

    private String product_content;

    private String product_info;

    private int product_oprice;

    private int product_sprice;

    private int product_rate;

    private Timestamp product_rdate;

    private int product_cnt;

    private String product_delete;

    private String product_img; //단일 이미지 url

    private int product_deliveryfee;

    private MultipartFile product_imgfile; //단일 이미지 파일

    private List<MultipartFile> product_images; //다중 이미지 파일

    private List<String> original_file; //원본파일 이름

    private List<String> file_url; // 다중 이미지 url 리스트

    private int category_num;

    private int order_cnt;


    public static ProductDTO toProductDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_num(productEntity.getProductNum());
        productDTO.setProduct_name(productEntity.getProductName());
        productDTO.setProduct_content(productEntity.getProductContent());
        productDTO.setProduct_info(productEntity.getProductInfo());
        productDTO.setProduct_oprice(productEntity.getProductOprice());
        productDTO.setProduct_sprice(productEntity.getProductSprice());
        productDTO.setProduct_rdate(productEntity.getProductRdate());
        productDTO.setProduct_cnt(productEntity.getProductCnt());
        productDTO.setProduct_delete(productEntity.getProductDelete());
        productDTO.setProduct_deliveryfee(productEntity.getProductDeliveryfee());
        productDTO.setProduct_img(productEntity.getProductImg());
        productDTO.setProduct_rate(productEntity.getProductRate());
        List<String> fileList=new ArrayList<>();
        for(FileEntity fileEntity:productEntity.getFileEntityList()){
            fileList.add(fileEntity.getFileUrl());
        }
        productDTO.setFile_url(fileList);
        productDTO.setCategory_num(productEntity.getCategoryEntity().getCategoryNum());
        return productDTO;
    }

}
