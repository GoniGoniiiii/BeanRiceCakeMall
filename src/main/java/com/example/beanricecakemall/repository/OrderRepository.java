package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
