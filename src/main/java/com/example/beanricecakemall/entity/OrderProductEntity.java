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


    public static OrderProductEntity toSave(OrderDTO orderDTO, OrderEntity orderEntity,ProductEntity productEntity, UserEntity userEntity){
        OrderProductEntity orderProductEntity=new OrderProductEntity();
        orderProductEntity.setOrderStatus("처리중");
        orderProductEntity.setOrderOprice(orderDTO.getOrder_oprice());
        orderProductEntity.setOrderPrice(orderDTO.getOrder_price());
        orderProductEntity.setOrderEntity(orderEntity);
        orderProductEntity.setProductEntity(productEntity);
        orderProductEntity.setUserEntity(userEntity);
        return orderProductEntity;
    }
}
