package com.example.beanricecakemall.entity;

import com.example.beanricecakemall.dto.DeliveryDTO;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_num")
    private OrderEntity orderEntity;

    public static DeliveryEntity toSave(DeliveryDTO deliveryDTO,OrderEntity orderEntity){
        DeliveryEntity deliveryEntity=new DeliveryEntity();
        deliveryEntity.setDeliveryUsername(deliveryDTO.getDelivery_userName());
        deliveryEntity.setDeliveryUseremail(deliveryDTO.getDelivery_userEmail());
        deliveryEntity.setDeliveryUsertel1(deliveryDTO.getDelivery_userTel1());
        deliveryEntity.setDeliveryUsertel2(deliveryDTO.getDelivery_userTel2());
        deliveryEntity.setDeliveryZipcode(deliveryDTO.getDelivery_zipcode());
        deliveryEntity.setDeliveryAddr(deliveryDTO.getDelivery_addr());
        deliveryEntity.setDeliveryAddrdetail(deliveryDTO.getDelivery_addrDetail());
        deliveryEntity.setDeliveryReq(deliveryDTO.getDelivery_req());
        deliveryEntity.setOrderEntity(orderEntity);
        return deliveryEntity;
    }


}
