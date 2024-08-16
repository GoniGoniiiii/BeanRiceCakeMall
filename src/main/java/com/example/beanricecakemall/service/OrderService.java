package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.DeliveryDTO;
import com.example.beanricecakemall.dto.OrderDTO;
import com.example.beanricecakemall.entity.DeliveryEntity;
import com.example.beanricecakemall.entity.OrderEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.DeliveryRepository;
import com.example.beanricecakemall.repository.OrderRepository;
import com.example.beanricecakemall.repository.UserRepository;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final DeliveryRepository deliveryRepository;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, DeliveryRepository deliveryRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.userRepository = userRepository;
    }

    public boolean insertOrder(OrderDTO orderDTO, DeliveryDTO deliveryDTO){
        if(orderDTO!=null && deliveryDTO!=null){
            System.out.println("service단 getUserNum : "+orderDTO.getUser_num());
            UserEntity userEntity=userRepository.findByUserNum(orderDTO.getUser_num());
            OrderEntity orderEntity= OrderEntity.toSave(orderDTO,userEntity);
            orderRepository.save(orderEntity);

            DeliveryEntity deliveryEntity2=DeliveryEntity.toSave(deliveryDTO,orderEntity);
            deliveryRepository.save(deliveryEntity2);

            return true;
        }
        return false;
    }
}
