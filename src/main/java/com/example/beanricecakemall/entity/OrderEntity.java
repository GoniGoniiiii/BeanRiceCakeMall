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
@Table(name="orderList")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_num")
    private int orderNum;

    @Column(nullable = false,name="order_userName")
    private String orderUserName;

    @Column(nullable = false,name="order_userEmail")
    private String orderUserEmail;

    @Column(nullable = false,name="order_userTel")
    private String orderUserTel;

    @Column(updatable = false,name="order_date")
    @CreationTimestamp
    private Timestamp orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_num",insertable = true,updatable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList=new ArrayList<>();

}
