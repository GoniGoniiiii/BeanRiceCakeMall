package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

    UserEntity findByUserNameAndUserEmail(String userName,String userEmail);

    UserEntity findByUserNameAndUserTel(String userName,String userTel);

    UserEntity findByUserIdAndUserNameAndUserEmail(String userId,String userName,String userEmail);
    UserEntity findByUserIdAndUserNameAndUserTel(String userId,String userName,String userTel);

    UserEntity findUserNumByUserId(String userId);

    UserEntity findByUserNum(int userNum);

}
