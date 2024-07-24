package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
