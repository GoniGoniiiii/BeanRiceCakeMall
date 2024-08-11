package com.example.beanricecakemall.service;

import com.example.beanricecakemall.entity.DeliveryEntity;
import com.example.beanricecakemall.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }
}
