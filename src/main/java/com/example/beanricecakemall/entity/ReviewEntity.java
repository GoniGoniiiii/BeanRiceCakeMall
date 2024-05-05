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
    private int review_num;

    @Column(nullable = false)
    private String review_title;

    @Column(nullable = false)
    private String review_content;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp review_rdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num",insertable = true,updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_num",updatable = false)
    private ProductEntity productEntity;


    @OneToMany(mappedBy = "reviewEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList=new ArrayList<>();

}
