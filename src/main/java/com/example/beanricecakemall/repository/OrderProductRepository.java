package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository  extends JpaRepository<OrderProductEntity,Integer> {
    
}
