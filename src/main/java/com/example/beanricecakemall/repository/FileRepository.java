package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {
    @Query("SELECT f FROM FileEntity f WHERE f.productEntity = :productEntity AND f.file_url LIKE %:mainImg%")
    Optional<FileEntity> findByProductEntityAndFile_urlStartingWith(@Param("productEntity") ProductEntity productEntity, @Param("mainImg") String mainImg);
}
