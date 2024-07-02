package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByUserId(String userId);

    UserEntity findByUserName (String userName);

    UserEntity findByUserId(String userId);
}
