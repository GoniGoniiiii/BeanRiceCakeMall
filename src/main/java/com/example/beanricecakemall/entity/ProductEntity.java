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
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_num;

    @Column(unique = true)
    private String product_name;

    @Column(nullable = false)
    private String product_content;

    @Column(nullable = false)
    private int product_oprice;

    @Column(nullable = false)
    private int product_sprice;

    @Column
    @CreationTimestamp
    private Timestamp product_rdate;

    @Column(nullable = false)
    private int product_cnt;

    @Column(length=1)
    private String product_delete;

    @Column
    private String product_img;

    @Column
    private int product_deliveryFee;
}
