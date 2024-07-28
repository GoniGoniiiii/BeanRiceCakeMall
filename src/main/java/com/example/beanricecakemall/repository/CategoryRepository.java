package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
}
