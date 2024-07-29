package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByCategoryEntityCategoryNum(int category_num);
}
