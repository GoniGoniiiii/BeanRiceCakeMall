package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="delivery")
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_num")
    private int deliveryNum;

    @Column(nullable = false,name="delivery_userName")
    private String deliveryUserName;

    @Column(nullable = false,name="delivery_userEmail")
    private String deliveryUserEmail;

    @Column(nullable = false,name="delivery_userTel1")
    private String deliveryUserTel1;

    @Column(name="delivery_userTel2")
    private String deliveryUserTel2;

    @Column(nullable = false,name="delivery_zipcode")
    private String deliveryZipcode;

    @Column(nullable = false,name="delivery_addr")
    private String deliveryAddr;

    @Column(nullable = false,name="delivery_addrDetail")
    private String deliveryAddrDetail;

    @Column(name="delivery_req")
    private String deliveryReq;

    @Column(length=1,name="delivery_def")
    private String deliveryDef;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_num")
    private OrderEntity orderEntity;


}
