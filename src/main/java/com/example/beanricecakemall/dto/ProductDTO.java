package com.example.beanricecakemall.dto;

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

    public int product_num;

    public String product_name;

    public String product_content;

    public int product_oprice;

    public int product_sprice;

    public Timestamp product_rdate;

    public int product_cnt;

    public String product_delete;

    public String product_img; //단일 이미지 url

    public int product_deleveryfee;

    public MultipartFile product_imgfile; //단일 이미지 파일

    private List<MultipartFile> product_images; //다중 이미지 파일

    public List<String> original_file; //원본파일 이름

    public List<String> file_url; // 다중 이미지 url 리스트



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
        productDTO.setProduct_deleveryfee(productEntity.getProduct_deliveryfee());
        List<String> fileList=new ArrayList<>();
        for(FileEntity fileEntity:productEntity.getFileEntityList()){
            fileList.add(fileEntity.getFile_url());
        }
        productDTO.setFile_url(fileList);
        return productDTO;
    }

}
