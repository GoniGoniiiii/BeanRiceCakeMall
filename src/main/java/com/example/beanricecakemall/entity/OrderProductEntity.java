package com.example.beanricecakemall.entity;

import com.example.beanricecakemall.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="orderproduct")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailNum;

    @Column
    private int orderCnt;

    @Column
    private int orderOprice;

    @Column
    private int orderPrice;

    @Column
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name="product_num")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name="order_num")
    private OrderEntity orderEntity;


    @ManyToOne
    @JoinColumn(name="user_num")
    private UserEntity userEntity;

}
