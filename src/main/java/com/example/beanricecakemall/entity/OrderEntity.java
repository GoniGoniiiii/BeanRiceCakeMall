package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="orderList")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_num;

    @Column(nullable = false)
    private String order_userName;

    @Column(nullable = false)
    private String order_userEmail;

    @Column(nullable = false)
    private String order_userTel;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp order_date;

}
