package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.OrderEntity;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private int order_num;

    private String order_userName;

    private String order_userEmail;

    private String order_userTel;

    private Timestamp order_date;

    private List<Integer> product_num;

    private int user_num;

    private int plus_point;

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
