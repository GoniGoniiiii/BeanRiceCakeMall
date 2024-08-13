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

    @Column(nullable = false,name="delivery_username")
    private String deliveryUsername;

    @Column(nullable = false,name="delivery_useremail")
    private String deliveryUseremail;

    @Column(nullable = false,name="delivery_usertel1")
    private String deliveryUsertel1;

    @Column(name="delivery_usertel2")
    private String deliveryUsertel2;

    @Column(nullable = false,name="delivery_zipcode")
    private String deliveryZipcode;

    @Column(nullable = false,name="delivery_addr")
    private String deliveryAddr;

    @Column(nullable = false,name="delivery_addrdetail")
    private String deliveryAddrdetail;

    @Column(name="delivery_req")
    private String deliveryReq;

    @Column(length=1,name="delivery_def")
    private String deliveryDef;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_num")
    private OrderEntity orderEntity;


}
