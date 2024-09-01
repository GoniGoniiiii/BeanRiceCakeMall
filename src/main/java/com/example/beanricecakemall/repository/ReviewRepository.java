package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {
}
