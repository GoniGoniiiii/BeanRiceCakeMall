package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.repository.CategoryRepository;
import com.example.beanricecakemall.repository.FileRepository;
import com.example.beanricecakemall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, FileRepository fileRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.fileRepository = fileRepository;
        this.categoryRepository=categoryRepository;
    }

    //할인율 구해주는 코드
    private void setDiscountRate(ProductEntity product) {
        if (product.getProduct_oprice() > 0 && product.getProduct_oprice() > product.getProduct_sprice()) {
            int discountRate = (int) ((product.getProduct_oprice() - product.getProduct_sprice()) / (double) product.getProduct_oprice() * 100);
            product.setProduct_rate(discountRate);
        } else {
            product.setProduct_rate(0);
        }
    }

    public void upload(ProductDTO productDTO){
        Optional<CategoryEntity> category=categoryRepository.findById(productDTO.getCategory_num());
        CategoryEntity categoryEntity=category.get();

        ProductEntity productEntity=ProductEntity.toProductEntity(productDTO,categoryEntity);
        int save_id=productRepository.save(productEntity).getProduct_num();

        if (productDTO.getProduct_imgfile() != null && !productDTO.getProduct_imgfile().isEmpty()) {
            MultipartFile imgfile = productDTO.getProduct_imgfile();
            String origianl_file = imgfile.getOriginalFilename();
            String file_url = "MainImg_" + System.currentTimeMillis() + "_" + origianl_file;
            String savePath = "D:/goni/image/" + file_url;
            System.out.println("대표 이미지 url : " + savePath);
            try {
                imgfile.transferTo(new File(savePath));
                ProductEntity product = productRepository.findById(save_id).get();

                FileEntity file = FileEntity.toFileEntity(productEntity, file_url);
                fileRepository.save(file);
            } catch (IOException e) {
                System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("대표 이미지가 등록되지 않았습니다.");
        }
        if (productDTO.getProduct_images() != null && !productDTO.getProduct_images().isEmpty()) {
            ProductEntity product = productRepository.findById(save_id).get();

            for (MultipartFile productFile : productDTO.getProduct_images()) {
                String original_file = productFile.getOriginalFilename();

                if (original_file != null && !original_file.isEmpty()) {
                    String file_url = System.currentTimeMillis() + "_" + original_file;
                    String savePath = "D:/goni/image" + file_url;
                    System.out.println("상품 설명 이미지 url " + savePath);
                    try {
                        productFile.transferTo(new File(savePath));
                        FileEntity file = FileEntity.toFileEntity(product, file_url);
                        fileRepository.save(file);
                    } catch (IOException e) {
                        System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
                    }
                } else {
                    System.out.println("상품 설명 이미지가 입력되지 않았습니다!");
                }
            }
        }
        //할인율 설정
        setDiscountRate(productEntity);
;
        
        //fileEntity에서 file_url 가져와서 대표이미지 product_img에 경로넣어주기
        Optional<FileEntity> mainImg=fileRepository.findByProductEntityAndFile_urlStartingWith(productEntity,"MainImg");
        mainImg.ifPresent( fileEntity -> {
            productEntity.setProduct_img(fileEntity.getFile_url());
            productRepository.save(productEntity);
        });
    }

    public List<ProductDTO>  productDTOList(){
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<ProductEntity> productEntities =productRepository.findAll();
        for(ProductEntity productEntity : productEntities){
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }

}