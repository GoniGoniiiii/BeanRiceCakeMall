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

    private String order_paymethod;

    private List<Integer> product_num;

    private int user_num;

    private int plus_point;

    private List<Integer> order_detail_num;

    private List<Integer> order_cnt;

    private List<Integer> order_oprice;

    private List<Integer> order_price;

    private List<String> order_status;

    private List<Integer> order_deliveryFee;

    public static OrderDTO toOrderDTO(OrderEntity orderEntity, List<Integer> productNums) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder_num(orderEntity.getOrderNum());
        orderDTO.setOrder_date(orderEntity.getOrderDate());
        orderDTO.setOrder_userTel(orderEntity.getOrderUserTel());
        orderDTO.setOrder_userEmail(orderEntity.getOrderUserEmail());
        orderDTO.setOrder_userName(orderEntity.getOrderUserName());
        orderDTO.setOrder_paymethod(orderEntity.getOrderPaymethod());
        orderDTO.setUser_num(orderEntity.getUserEntity().getUserNum());
        orderDTO.setProduct_num(productNums);
        return orderDTO;
    }

}
