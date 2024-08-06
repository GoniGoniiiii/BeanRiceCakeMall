package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    public List<CartEntity> findAllByUserEntityUserNum(int user_num);

}
