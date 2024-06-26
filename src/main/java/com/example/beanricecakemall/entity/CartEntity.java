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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_num;

    @Column
    @CreationTimestamp
    private Timestamp cart_date;

    @Column(nullable = false)
    private int cart_cnt;

    @OneToMany(mappedBy = "cartEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList=new ArrayList<>();
}
