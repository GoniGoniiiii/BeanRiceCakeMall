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
    private int delivery_num;

    @Column(nullable = false)
    private String delivery_userName;

    @Column(nullable = false)
    private String delivery_userEmail;

    @Column(nullable = false)
    private String delivery_userTel1;

    @Column
    private String delivery_userTel2;

    @Column(nullable = false)
    private String delivery_zipcode;

    @Column(nullable = false)
    private String delivery_addr;

    @Column(nullable = false)
    private String delivery_addrDetail;

    @Column
    private String delivery_req;

    @Column(length=1)
    private String delivery_def;



}
