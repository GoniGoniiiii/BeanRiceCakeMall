package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {
    List<ReviewEntity> findByProductEntityProductNum(int product_num);

    List<ReviewEntity> findTop2ByProductEntityProductNumOrderByReviewRdateDesc(int product_num);

    //사용자가 이 상품에 대해 리뷰를 쓴 적이 있는지!
    boolean existsByProductEntityProductNumAndUserEntityUserNum(int product_num, int user_num);

}
