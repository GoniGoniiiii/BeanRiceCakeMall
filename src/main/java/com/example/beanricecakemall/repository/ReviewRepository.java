package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {
    List<ReviewEntity> findByProductEntityProductNum(int product_num);


}
