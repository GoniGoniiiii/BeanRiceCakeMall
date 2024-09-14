package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.ProductDTO;
import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.repository.CategoryRepository;
import com.example.beanricecakemall.repository.FileRepository;
import com.example.beanricecakemall.repository.ProductRepository;
import jakarta.transaction.Transactional;
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

    public ProductService(ProductRepository productRepository, FileRepository fileRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.fileRepository = fileRepository;
        this.categoryRepository = categoryRepository;
    }

    //할인율 구해주는 코드
    private void setDiscountRate(ProductEntity product) {
        if (product.getProductOprice() > 0 && product.getProductOprice() > product.getProductSprice()) {
            int discountRate = (int) ((product.getProductOprice() - product.getProductSprice()) / (double) product.getProductOprice() * 100);
            product.setProductRate(discountRate);
        } else {
            product.setProductRate(0);
        }
    }

    public void upload(ProductDTO productDTO) {
        Optional<CategoryEntity> category = categoryRepository.findById(productDTO.getCategory_num());
        CategoryEntity categoryEntity = category.get();

        ProductEntity productEntity = ProductEntity.toProductEntity(productDTO, categoryEntity);
        int save_id = productRepository.save(productEntity).getProductNum();

        if (productDTO.getProduct_imgfile() != null && !productDTO.getProduct_imgfile().isEmpty()) {
            MultipartFile imgfile = productDTO.getProduct_imgfile();
            String origianl_file = imgfile.getOriginalFilename();
            String file_url = "MainImg_" + System.currentTimeMillis() + "_" + origianl_file;
            String savePath = "C:/goni/image/" + file_url;
            System.out.println("대표 이미지 url : " + savePath);
            try {
                imgfile.transferTo(new File(savePath));
                ProductEntity product = productRepository.findById(save_id).get();

                FileEntity file = FileEntity.toFileEntity(productEntity, file_url);
                productEntity.setProductImg(file_url);
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
                    String savePath = "C:/goni/image/" + file_url;
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

        productRepository.save(productEntity);
    }

    public List<ProductDTO> productDTOList(int category_num) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<ProductEntity> productEntities;

        if (category_num == 13) { //전체 상품 출력
            productEntities = productRepository.findAll();
        } else {
            productEntities = productRepository.findAllByCategoryEntityCategoryNum(category_num);
        }
        for (ProductEntity productEntity : productEntities) {
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }

    public ProductDTO productDetail(int product_num) {
        Optional<ProductEntity> productEntities = productRepository.findById(product_num);

        if (productEntities.isPresent()) {
            ProductEntity productEntity = productEntities.get();
            ProductDTO productDTO = ProductDTO.toProductDTO(productEntity);
            return productDTO;
        }
        return null;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<CategoryEntity> category = categoryRepository.findById(productDTO.getCategory_num());
        CategoryEntity categoryEntity = category.get();

        ProductEntity productEntity = ProductEntity.updateEntity(productDTO, categoryEntity);
        int save_id = productRepository.save(productEntity).getProductNum();
//
//        if(productDTO.getProduct_imgfile() ==null || productDTO.getProduct_imgfile().isEmpty()){
//            String mainImg=productDTO.getProduct_img();
//            productEntity.setProductImg(mainImg);
//            FileEntity file=FileEntity.toFileEntity(productEntity,mainImg);
//            fileRepository.save(file);
//        }

        if (productDTO.getProduct_imgfile() != null && !productDTO.getProduct_imgfile().isEmpty()) {
            MultipartFile imgfile = productDTO.getProduct_imgfile();
            String origianl_file = imgfile.getOriginalFilename();
            String file_url = "MainImg_" + System.currentTimeMillis() + "_" + origianl_file;
            String savePath = "C:/goni/image/" + file_url;
            System.out.println("대표 이미지 url : " + savePath);
            try {
                imgfile.transferTo(new File(savePath));
                ProductEntity product = productRepository.findById(save_id).get();

                FileEntity file = FileEntity.toFileEntity(productEntity, file_url);
                productEntity.setProductImg(file_url);
                fileRepository.save(file);
            } catch (IOException e) {
                System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
            }
        } else if (productDTO.getProduct_img() != null && !productDTO.getProduct_img().isEmpty()) {
            // 유지할 이미지가 이미 설정되어 있는 경우
            System.out.println("대표 이미지는 유지됩니다.");
        }

        if (productDTO.getProduct_images() != null && !productDTO.getProduct_images().isEmpty()) {
            ProductEntity product = productRepository.findById(save_id).get();

            for (MultipartFile productFile : productDTO.getProduct_images()) {
                String original_file = productFile.getOriginalFilename();

                if (original_file != null && !original_file.isEmpty()) {
                    String file_url = System.currentTimeMillis() + "_" + original_file;
                    String savePath = "C:/goni/image/" + file_url;
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

        ProductDTO product = ProductDTO.toProductDTO(productEntity);
        return productDTO;
    }

    public void imgDelete(String file_url) { //상품 업로드,업데이트 할때 이미지 삭제할 수 있게!
        // 데이터베이스에서 파일 엔티티 조회
        FileEntity file = fileRepository.findByFileUrl(file_url);

        if (file != null) {
            fileRepository.delete(file);
        } else {
            System.out.println("삭제할 파일이 없음");
        }
    }

    public String findProductName(int product_num) {
        ProductEntity product = productRepository.findAllByProductNum(product_num);
        if (product != null) {
            return product.getProductName();
        }
        return null;
    }

    public int findProductSprice(int product_num) {
        ProductEntity product = productRepository.findAllByProductNum(product_num);
        if (product != null) {
            return product.getProductSprice();
        }
        return 0;
    }

    public int findProductDeliveryFee(int product_num) {
        ProductEntity product = productRepository.findAllByProductNum(product_num);
        if (product != null) {
            return product.getProductDeliveryfee();
        }
        return 0;
    }

    public int findProductOprice(int product_num) {
        ProductEntity product = productRepository.findAllByProductNum(product_num);
        if (product != null) {
            return product.getProductOprice();
        }
        return 0;
    }

    @Transactional
    public void deleteProduct(int product_num) {
        productRepository.deleteAllByProductNum(product_num);

    }

    public ProductDTO findProductInfo(int product_num) {
        ProductEntity product = productRepository.findByProductNum(product_num);
        ProductDTO productDTO = ProductDTO.toProductDTO(product);
        return productDTO;
    }

    public String findProductImg(int product_num) {
        ProductEntity productEntity = productRepository.findByProductNum(product_num);
        String product_img = productEntity.getProductImg();
        System.out.println("Service내 product_img : " + product_img);
        return product_img;
    }

    public List<ProductDTO> search(String keyword) {
        List<ProductEntity> productEntities = productRepository.findByProductNameContaining(keyword);
        List<ProductDTO> productDTOList = new ArrayList<>();

        if (productEntities != null) {
            for (ProductEntity product : productEntities) {
                productDTOList.add(ProductDTO.toProductDTO(product));
            }
            return productDTOList;
        } else {
            System.out.println("검색 결과가 존재하지 않습니다!");
        }
        return null;
    }

    public List<ProductDTO> sortProductList(int category_num, String sort) {
        List<ProductEntity> productEntities = new ArrayList<>();
        System.out.println("category_num : " + category_num);
        System.out.println("sort : " + sort);

        if ("new".equals(sort)) { //최신등록순
            if (category_num == 13) {
                productEntities = productRepository.findAllByOrderByProductRdateDesc();
            } else {
                productEntities = productRepository.findByCategoryEntityCategoryNumOrderByProductRdateDesc(category_num);
            }
        } else if ("lowPrice".equals(sort)) { //낮은가격순
            if (category_num == 13) {
                productEntities = productRepository.findAllByOrderByProductSprice();
            } else {
                productEntities = productRepository.findByCategoryEntityCategoryNumOrderByProductSprice(category_num);
            }
        } else if ("highPrice".equals(sort)) { //높은가격순
            if (category_num == 13) {
                productEntities = productRepository.findAllByOrderByProductSpriceDesc();
            } else {
                productEntities = productRepository.findByCategoryEntityCategoryNumOrderByProductSpriceDesc(category_num);
            }
        } else if ("lowRate".equals(sort)) { //낮은할인율순
            if (category_num == 13) {
                productEntities = productRepository.findAllByOrderByProductRate();
            } else {
                productEntities = productRepository.findByCategoryEntityCategoryNumOrderByProductRate(category_num);
            }
        } else if ("highRate".equals(sort)) { //높은할인율순
            if (category_num == 13) {
                productEntities = productRepository.findAllByOrderByProductRateDesc();
            } else {
                productEntities = productRepository.findByCategoryEntityCategoryNumOrderByProductRateDesc(category_num);
            }
        }
        List<ProductDTO> sortProductList = new ArrayList<>();

        for (ProductEntity product : productEntities) {
            sortProductList.add(ProductDTO.toProductDTO(product));
            System.out.println(sortProductList.toString());
        }
        return sortProductList;
    }
}
