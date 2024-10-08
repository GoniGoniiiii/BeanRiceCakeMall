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

    private int use_point;

    private List<Integer> order_detail_num;

    private List<Integer> order_cnt;
    
    //상품 구매금액
    private List<Integer> order_oprice;

    private List<String> order_status;

    //최종 결제금액
    private int order_price;

    private int order_deliveryfee;

    private int order_saleprice;

    public static OrderDTO toOrderDTO(OrderEntity orderEntity, List<Integer> productNums) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder_num(orderEntity.getOrderNum());
        orderDTO.setOrder_date(orderEntity.getOrderDate());
        orderDTO.setOrder_userTel(orderEntity.getOrderUserTel());
        orderDTO.setOrder_userEmail(orderEntity.getOrderUserEmail());
        orderDTO.setOrder_userName(orderEntity.getOrderUserName());
        orderDTO.setOrder_paymethod(orderEntity.getOrderPaymethod());
        orderDTO.setUser_num(orderEntity.getUserEntity().getUserNum());
        orderDTO.setOrder_price(orderEntity.getOrderPrice());
        orderDTO.setOrder_deliveryfee(orderEntity.getOrderDeliveryfee());
        orderDTO.setOrder_saleprice(orderEntity.getOrderSaleprice());
        orderDTO.setProduct_num(productNums);
        return orderDTO;
    }

}
