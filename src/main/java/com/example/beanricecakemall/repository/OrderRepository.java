package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUserEntityUserId(String userId);
}
