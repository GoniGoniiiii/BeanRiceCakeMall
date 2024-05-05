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
    @JoinColumn(name="product_num",insertable = true, updatable = false)
    private ProductEntity productEntity;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="review_num",insertable = true,updatable = false)
    private ReviewEntity reviewEntity;

}
