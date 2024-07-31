package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_num")
    private int reviewNum;

    @Column(nullable = false,name="review_title")
    private String reviewTitle;

    @Column(nullable = false,name="review_content")
    private String reviewContent;

    @Column(updatable = false,name="review_rdate")
    @CreationTimestamp
    private Timestamp reviewRdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num",insertable = true,updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_num",updatable = false)
    private ProductEntity productEntity;


    @OneToMany(mappedBy = "reviewEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList=new ArrayList<>();

}
