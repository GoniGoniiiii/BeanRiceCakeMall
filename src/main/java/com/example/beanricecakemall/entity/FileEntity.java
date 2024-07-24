package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int file_num;

    @Column
    private String file_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_num",insertable = true)
    private ProductEntity productEntity;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="review_num",insertable = true ,updatable = false)
    private ReviewEntity reviewEntity;

    public static FileEntity toFileEntity(ProductEntity productEntity,String file_url){
        FileEntity fileEntity=new FileEntity();
        fileEntity.setFile_url(file_url);
        fileEntity.setProductEntity(productEntity);
        return fileEntity;
    }
}
