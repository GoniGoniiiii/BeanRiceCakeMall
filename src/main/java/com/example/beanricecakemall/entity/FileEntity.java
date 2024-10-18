package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Entity
@Getter
@Setter
@Table(name="file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_num")
    private int fileNum;

    @Column(name="file_url")
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_num")
    private ProductEntity productEntity;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="review_num")
    private ReviewEntity reviewEntity;

    public static FileEntity toFileEntity(ProductEntity productEntity,String file_url){
        FileEntity fileEntity=new FileEntity();
        fileEntity.setFileUrl(file_url);
        fileEntity.setProductEntity(productEntity);
        return fileEntity;
    }

    public static FileEntity toUpdateFileEntity(ProductEntity productEntity,String file_url,int file_num){
        FileEntity fileEntity=new FileEntity();
        fileEntity.setFileNum(file_num);
        fileEntity.setFileUrl(file_url);
        fileEntity.setProductEntity(productEntity);
        return fileEntity;
    }

    public static FileEntity toFileEntity(ReviewEntity reviewEntity,String file_url){
        FileEntity fileEntity=new FileEntity();
        fileEntity.setFileUrl(file_url);
        fileEntity.setReviewEntity(reviewEntity);
        return fileEntity;
    }
}
