package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByCategoryEntityCategoryNum(int category_num);

    ProductEntity findAllByProductNum(int product_num);

    void deleteAllByProductNum(int product_num);

    ProductEntity  findByProductNum(int product_num);

    List<ProductEntity> findByProductNameContaining(String keyword);

    //최신등록순
    List<ProductEntity> findByProductNameContainingOrderByProductRdateDesc(String keyword);
    //낮은가격순
    List<ProductEntity> findByProductNameContainingOrderByProductSprice(String keyword);
    //높은가격순
    List<ProductEntity> findByProductNameContainingOrderByProductSpriceDesc(String keyword);
    //낮은할인율순
    List<ProductEntity> findByProductNameContainingOrderByProductRate(String keyword);
    //높은할인율순
    List<ProductEntity> findByProductNameContainingOrderByProductRateDesc(String keyword);

    //최신등록순
    List<ProductEntity> findByCategoryEntityCategoryNumOrderByProductRdateDesc(int category_num);
    //낮은가격순
    List<ProductEntity> findByCategoryEntityCategoryNumOrderByProductSprice(int category_num);
    //높은가격순
    List<ProductEntity> findByCategoryEntityCategoryNumOrderByProductSpriceDesc(int category_num);
    //낮은할인율순
    List<ProductEntity> findByCategoryEntityCategoryNumOrderByProductRate(int category_num);
    //높은할인율순
    List<ProductEntity> findByCategoryEntityCategoryNumOrderByProductRateDesc(int category_num);

    //전체 최신등록순
    List<ProductEntity> findAllByOrderByProductRdateDesc();
    //전체 낮은가격순
    List<ProductEntity> findAllByOrderByProductSprice();
    //전체 높은가격순
    List<ProductEntity> findAllByOrderByProductSpriceDesc();
    //전체 낮은할인율순
    List<ProductEntity> findAllByOrderByProductRate();
    //전체 높은할인율순
    List<ProductEntity> findAllByOrderByProductRateDesc();

}
