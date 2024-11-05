package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.ReviewDTO;
import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.entity.ReviewEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.FileRepository;
import com.example.beanricecakemall.repository.ProductRepository;
import com.example.beanricecakemall.repository.ReviewRepository;
import com.example.beanricecakemall.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository, FileRepository fileRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
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

        boolean exist = reviewRepository.existsByProductEntityProductNumAndUserEntityUserNum(reviewDTO.getProduct_num(), reviewDTO.getUser_num());
        System.out.println("리뷰 작성 여부 : " + exist);

        if (!exist) {
            ReviewEntity reviewEntity = new ReviewEntity();
            reviewEntity.setReviewTitle(reviewDTO.getReview_title());
            reviewEntity.setReviewContent(reviewDTO.getReview_content());
            reviewEntity.setProductEntity(productEntity);
            reviewEntity.setUserEntity(userEntity);

            ReviewEntity review = reviewRepository.save(reviewEntity);

            if (reviewDTO.getReview_img() != null && !reviewDTO.getReview_img().isEmpty()) {
                for (MultipartFile file : reviewDTO.getReview_img()) {
                    String original_file = file.getOriginalFilename();

                    if (original_file != null && !original_file.isEmpty()) {
                        String file_url = System.currentTimeMillis() + "_" + original_file;
                        String savePath = "/home/ubuntu/bean/image/" + file_url;
                        System.out.println("포토 리뷰 이미지 url :  " + savePath);
                        try {
                            file.transferTo(new File(savePath));
                            FileEntity fileEntity = FileEntity.toFileEntity(review, file_url);
                            fileRepository.save(fileEntity);
                        } catch (IOException e) {
                            System.out.println("파일저장중 오류 발생 : " + e.getMessage());
                        }
                    }
                }
            }
                return "리뷰가 정상적으로 추가되었습니다!";
        }
        return "이미 작성된 리뷰입니다!";
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
        if (reviewDTO.getReview_img() != null && !reviewDTO.getReview_img().isEmpty()) {
            System.out.println("review_num : " + reviewDTO.getReview_num());

            for (MultipartFile reviewImg : reviewDTO.getReview_img()) {
                String original_file = reviewImg.getOriginalFilename();
                System.out.println("original_file: " + original_file);

                if (original_file != null && !original_file.isEmpty()) {
                    String file_url = System.currentTimeMillis() + "_" + original_file;
                    String savePath = "/home/ubuntu/bean/image/" + file_url;
                    System.out.println("상품 설명 이미지 url " + savePath);
                    try {
                        reviewImg.transferTo(new File(savePath));
                        FileEntity file = FileEntity.toFileEntity(review, file_url);
                        fileRepository.save(file);
                    } catch (IOException e) {
                        System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
                    }
                } else {
                    System.out.println("상품 설명 이미지가 입력되지 않았습니다!");
                }
            }
        }
        return "리뷰가 정상적으로 수정되었습니다!";
    }

    public List<ReviewDTO> ListReview(int product_num) {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        List<ReviewEntity> reviewEntities = reviewRepository.findByProductEntityProductNum(product_num);
        if (reviewEntities != null) {
            for (ReviewEntity review : reviewEntities) {
                ReviewDTO reviewDTO = new ReviewDTO();

                List<FileEntity> file = fileRepository.findByReviewEntityReviewNum(review.getReviewNum());
                List<String> Images = new ArrayList<>();
                for (FileEntity fileEntity : file) {
                    Images.add(fileEntity.getFileUrl());
                }

                reviewDTO.setReview_num(review.getReviewNum());
                reviewDTO.setReview_title(review.getReviewTitle());
                reviewDTO.setReview_content(review.getReviewContent());
                reviewDTO.setReview_rdate(review.getReviewRdate());
                reviewDTO.setProduct_num(review.getProductEntity().getProductNum());
                reviewDTO.setUser_id(review.getUserEntity().getUserId());
                reviewDTO.setReview_images(Images);

                System.out.println("reviewUserID :" + reviewDTO.getUser_id());
                reviewDTOList.add(reviewDTO);
                System.out.println("[service] reviewDTO : " + reviewDTO.toString());
            }
        }
        return reviewDTOList;
    }

    public List<ReviewDTO> RecentListReview(int product_num) {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        List<ReviewEntity> reviewEntities = reviewRepository.findTop2ByProductEntityProductNumOrderByReviewRdateDesc(product_num);
        if (reviewEntities != null) {
            for (ReviewEntity review : reviewEntities) {
                ReviewDTO reviewDTO = new ReviewDTO();

                reviewDTO.setReview_num(review.getReviewNum());
                reviewDTO.setReview_title(review.getReviewTitle());
                reviewDTO.setReview_content(review.getReviewContent());
                reviewDTO.setReview_rdate(review.getReviewRdate());
                reviewDTO.setProduct_num(review.getProductEntity().getProductNum());
                reviewDTO.setUser_id(review.getUserEntity().getUserId());

                reviewDTOList.add(reviewDTO);
                System.out.println("[service] 최근 2개 리뷰 reviewDTO : " + reviewDTO.toString());
            }
        }
        return reviewDTOList;
    }

    @Transactional
    public String deleteReview(int review_num) {
        reviewRepository.deleteById(review_num);
        if (reviewRepository.findById(review_num).isPresent()) {
            return "요청이 정상적으로 처리되지 않았습니다.";
        } else {
            return "리뷰가 정상적으로 삭제되었습니다!";
        }
    }

    public int reviewCount(int product_num) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByProductEntityProductNum(product_num);
        int count = 0;
        for (ReviewEntity review : reviewEntities) {
            count++;
            System.out.println("리뷰건수 :   " + count);
        }
        return count;
    }
}

