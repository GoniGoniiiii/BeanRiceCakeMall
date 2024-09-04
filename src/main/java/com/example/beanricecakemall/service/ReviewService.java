package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.ReviewDTO;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.entity.ReviewEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.ProductRepository;
import com.example.beanricecakemall.repository.ReviewRepository;
import com.example.beanricecakemall.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public String insertReview(ReviewDTO reviewDTO) {
        if (reviewDTO == null) {
            return "리뷰 정보가 없습니다. 다시 시도해주세요.";
        }

        if (reviewDTO.getReview_title() == null || reviewDTO.getReview_title().isEmpty()) {
            return "리뷰 제목을 입력해주세요.";
        }
        if (reviewDTO.getReview_content() == null || reviewDTO.getReview_content().isEmpty()) {
            return "리뷰 내용을 입력해주세요.";
        }

        ProductEntity productEntity = productRepository.findAllByProductNum(reviewDTO.getProduct_num());
        UserEntity userEntity = userRepository.findByUserNum(reviewDTO.getUser_num());

        if (productEntity == null) {
            return "유효한 상품을 찾을 수 없습니다.";
        }
        if (userEntity == null) {
            return "유효한 사용자를 찾을 수 없습니다.";
        }

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewTitle(reviewDTO.getReview_title());
        reviewEntity.setReviewContent(reviewDTO.getReview_content());
        reviewEntity.setProductEntity(productEntity);
        reviewEntity.setUserEntity(userEntity);

        ReviewEntity review = reviewRepository.save(reviewEntity);
        if (review != null) {
            return "리뷰가 정상적으로 추가되었습니다!";
        } else {
            return "리뷰가 정상적으로 추가되지 않았습니다! 다시 시도해주세요.";
        }
    }


    public String updateReview(ReviewDTO reviewDTO) {

        ProductEntity productEntity = productRepository.findAllByProductNum(reviewDTO.getProduct_num());
        UserEntity userEntity = userRepository.findByUserNum(reviewDTO.getUser_num());


        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewNum(reviewDTO.getReview_num());
        reviewEntity.setReviewTitle(reviewDTO.getReview_title());
        reviewEntity.setReviewContent(reviewDTO.getReview_content());
        reviewEntity.setProductEntity(productEntity);
        reviewEntity.setUserEntity(userEntity);

        ReviewEntity review = reviewRepository.save(reviewEntity);
        
        if (review != null) {
            return "리뷰가 정상적으로 수정되었습니다!";
        } else {
            return "리뷰가 정상적으로 수정되지 않았습니다! 다시 시도해주세요.";
        }
    }

    public List<ReviewDTO> ListReview(int product_num){
        List<ReviewDTO> reviewDTOList=new ArrayList<>();
        List<ReviewEntity> reviewEntities=reviewRepository.findByProductEntityProductNum(product_num);
        if(reviewEntities!=null){
            for(ReviewEntity review:reviewEntities){
                ReviewDTO reviewDTO=new ReviewDTO();

                reviewDTO.setReview_num(review.getReviewNum());
                reviewDTO.setReview_title(review.getReviewTitle());
                reviewDTO.setReview_content(review.getReviewContent());
                reviewDTO.setReview_rdate(review.getReviewRdate());
                reviewDTO.setProduct_num(review.getProductEntity().getProductNum());

                reviewDTOList.add(reviewDTO);
                System.out.println("[service] reviewDTO : "+reviewDTO.toString());
            }
        }
        return reviewDTOList;
    }



}

