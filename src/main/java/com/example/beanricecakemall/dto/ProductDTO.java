package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int product_num;

    private String product_name;

    private String product_content;

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



    public static ProductDTO toProductDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_num(productEntity.getProduct_num());
        productDTO.setProduct_name(productEntity.getProduct_name());
        productDTO.setProduct_content(productEntity.getProduct_content());
        productDTO.setProduct_oprice(productEntity.getProduct_oprice());
        productDTO.setProduct_sprice(productEntity.getProduct_sprice());
        productDTO.setProduct_rdate(productEntity.getProduct_rdate());
        productDTO.setProduct_cnt(productEntity.getProduct_cnt());
        productDTO.setProduct_delete(productEntity.getProduct_delete());
        productDTO.setProduct_deliveryfee(productEntity.getProduct_deliveryfee());
        productDTO.setProduct_img(productEntity.getProduct_img());
        productDTO.setProduct_rate(productEntity.getProduct_rate());
        List<String> fileList=new ArrayList<>();
        for(FileEntity fileEntity:productEntity.getFileEntityList()){
            fileList.add(fileEntity.getFile_url());
        }
        productDTO.setFile_url(fileList);
        productDTO.setCategory_num(productEntity.getCategoryEntity().getCategory_num());
        return productDTO;
    }

}
