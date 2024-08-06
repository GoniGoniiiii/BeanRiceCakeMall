package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
}
