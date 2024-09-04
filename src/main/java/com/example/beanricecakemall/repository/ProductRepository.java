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

}
