package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {

    FileEntity findByFileUrl(String file_url);

    List<FileEntity> findByProductEntityProductNum(int product_num);

    List<FileEntity> findByReviewEntityReviewNum(int review_num);
}
