package com.example.beanricecakemall.entity;

import com.example.beanricecakemall.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.query.Order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orderlist")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_num")
    private int orderNum;

    @Column(nullable = false,name="order_username")
    private String orderUserName;

    @Column(nullable = false,name="order_useremail")
    private String orderUserEmail;

    @Column(nullable = false,name="order_usertel")
    private String orderUserTel;

    @Column(updatable = false,name="order_date")
    @CreationTimestamp
    private Timestamp orderDate;

    @Column(name="order_paymethod")
    private String orderPaymethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_num",insertable = true,updatable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderProductEntity> orderProductEntity=new ArrayList<>();

    @OneToOne(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    private DeliveryEntity deliveryEntity;


    public static OrderEntity toSave(OrderDTO orderDTO,UserEntity userEntity){
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderUserTel(orderDTO.getOrder_userTel());
        orderEntity.setOrderUserEmail(orderDTO.getOrder_userEmail());
        orderEntity.setOrderUserName(orderDTO.getOrder_userName());
        orderEntity.setOrderPaymethod(orderDTO.getOrder_paymethod());
        orderEntity.setUserEntity(userEntity);
        return orderEntity;
    }
}
