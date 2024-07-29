package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="cart_num")
    private int cartNum;

    @Column(name="cart_date")
    @CreationTimestamp
    private Timestamp cartDate;

    @Column(nullable = false , name="cart_cnt")
    private int cartCnt;

    @OneToMany(mappedBy = "cartEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList=new ArrayList<>();
}
