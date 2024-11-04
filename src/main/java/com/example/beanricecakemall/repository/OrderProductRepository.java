package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository  extends JpaRepository<OrderProductEntity,Integer> {
    List<OrderProductEntity> findByOrderEntityOrderNum(int orderNum);
}
