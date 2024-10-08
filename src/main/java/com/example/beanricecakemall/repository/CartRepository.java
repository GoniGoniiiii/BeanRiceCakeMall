package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    public List<CartEntity> findAllByUserEntityUserNum(int user_num);

    public void deleteByProductEntityProductNumAndUserEntityUserNum(int product_num,int user_num);

    public boolean existsByProductEntityProductNum(int product_num);

    public CartEntity findCartCntByProductEntityProductNum(int product_num);
}
