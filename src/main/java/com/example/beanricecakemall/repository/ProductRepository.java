package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.category_num = :category_num")
    List<ProductEntity> findAllByCategoryEntity_Category_num(int category_num);
}
