package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int order_num;

    private String order_userName;

    private String order_userEmail;

    private String order_userTel;

    private Timestamp order_date;

    private String product_num;

    public static OrderDTO toOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrder_num(orderEntity.getOrderNum());
        orderDTO.setOrder_date(orderEntity.getOrderDate());
        orderDTO.setOrder_userTel(orderEntity.getOrderUserTel());
        orderDTO.setOrder_userEmail(orderEntity.getOrderUserEmail());
        orderDTO.setOrder_userName(orderEntity.getOrderUserName());
        return orderDTO;
    }
}
