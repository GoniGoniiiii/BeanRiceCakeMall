package com.example.beanricecakemall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int order_num;

    private String order_userName;

    private String order_userEmail;

    private String order_userTel;

    private String order_date;

    private String product_num;
}
