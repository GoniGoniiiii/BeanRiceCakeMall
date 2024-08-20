package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.OrderEntity;
import com.example.beanricecakemall.entity.OrderProductEntity;
import com.example.beanricecakemall.entity.ProductEntity;
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

    private String order_paymethod;

    private List<Integer> product_num;

    private int user_num;

    private int plus_point;

    private int order_cnt;

    private int order_oprice;

    private int order_price;

    private String order_status;

    public static OrderDTO toOrderDTO(OrderEntity orderEntity, OrderProductEntity orderProductEntity){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrder_num(orderEntity.getOrderNum());
        orderDTO.setOrder_date(orderEntity.getOrderDate());
        orderDTO.setOrder_userTel(orderEntity.getOrderUserTel());
        orderDTO.setOrder_userEmail(orderEntity.getOrderUserEmail());
        orderDTO.setOrder_userName(orderEntity.getOrderUserName());
        orderDTO.setOrder_paymethod(orderEntity.getOrderPaymethod());
        orderDTO.setOrder_cnt(orderProductEntity.getOrderCnt());
        orderDTO.setOrder_oprice(orderProductEntity.getOrderOprice());
        orderDTO.setOrder_price(orderProductEntity.getOrderPrice());
        orderDTO.setOrder_status(orderProductEntity.getOrderStatus());
        return orderDTO;
    }
}
