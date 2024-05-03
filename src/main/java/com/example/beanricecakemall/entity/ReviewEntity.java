package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;

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


}
