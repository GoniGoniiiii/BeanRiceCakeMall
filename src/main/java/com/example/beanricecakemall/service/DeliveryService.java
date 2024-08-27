package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.DeliveryDTO;
import com.example.beanricecakemall.entity.DeliveryEntity;
import com.example.beanricecakemall.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public DeliveryDTO deliveryInfo(int order_num){
        DeliveryEntity deliveryEntity=deliveryRepository.findByOrderEntityOrderNum(order_num);
        DeliveryDTO deliveryDTO=DeliveryDTO.toDeliveryDTO(deliveryEntity);
        return deliveryDTO;
    }


}
