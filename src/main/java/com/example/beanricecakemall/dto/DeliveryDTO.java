package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.DeliveryEntity;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class DeliveryDTO {
    private int delivery_num;

    private String delivery_userName;

    private String delivery_userEmail;

    private String delivery_userTel1;

    private String delivery_userTel2;

    private String delivery_zipcode;

    private String delivery_addr;

    private String delivery_addrDetail;

    private String delivery_req;


    public static DeliveryDTO toDeliveryDTO(DeliveryEntity deliveryEntity){
        DeliveryDTO deliveryDTO=new DeliveryDTO();
        deliveryDTO.setDelivery_num(deliveryEntity.getDeliveryNum());
        deliveryDTO.setDelivery_userName(deliveryEntity.getDeliveryUsername());
        deliveryDTO.setDelivery_userEmail(deliveryEntity.getDeliveryUseremail());
        deliveryDTO.setDelivery_userTel1(deliveryEntity.getDeliveryUsertel1());
        deliveryDTO.setDelivery_userTel2(deliveryEntity.getDeliveryUsertel2());
        deliveryDTO.setDelivery_zipcode(deliveryEntity.getDeliveryZipcode());
        deliveryDTO.setDelivery_addr(deliveryEntity.getDeliveryAddr());
        deliveryDTO.setDelivery_addrDetail(deliveryEntity.getDeliveryAddrdetail());
        deliveryDTO.setDelivery_req(deliveryEntity.getDeliveryReq());
        return deliveryDTO;
    }
}
