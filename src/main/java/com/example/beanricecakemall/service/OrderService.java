package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.DeliveryDTO;
import com.example.beanricecakemall.dto.OrderDTO;
import com.example.beanricecakemall.entity.DeliveryEntity;
import com.example.beanricecakemall.entity.OrderEntity;
import com.example.beanricecakemall.entity.OrderProductEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.DeliveryRepository;
import com.example.beanricecakemall.repository.OrderProductRepository;
import com.example.beanricecakemall.repository.OrderRepository;
import com.example.beanricecakemall.repository.UserRepository;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final DeliveryRepository deliveryRepository;

    private final UserRepository userRepository;

    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, DeliveryRepository deliveryRepository, UserRepository userRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.userRepository = userRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public int insertOrder(OrderDTO orderDTO, DeliveryDTO deliveryDTO) {
        if (orderDTO != null && deliveryDTO != null) {
            System.out.println("service단 getUserNum : " + orderDTO.getUser_num());
            UserEntity userEntity = userRepository.findByUserNum(orderDTO.getUser_num());
            OrderEntity orderEntity = OrderEntity.toSave(orderDTO, userEntity);
            orderRepository.save(orderEntity);

            DeliveryEntity deliveryEntity2 = DeliveryEntity.toSave(deliveryDTO, orderEntity);
            deliveryRepository.save(deliveryEntity2);

            return orderEntity.getOrderNum();
        }
        return 0;
    }

    public List<OrderDTO> orderList(String user_id) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByUserEntityUserId(user_id);
        List<OrderProductEntity> orderProductEntities = new ArrayList<>();

        int order_num = 0;
        for (OrderEntity order : orderEntities) {
            //order_num을 구해서
            order_num = order.getOrderNum();
            System.out.println("order_num :" + order_num);
            //뽑아온 order_num을 가지고 있는 orderProduct 뽑아오기
            orderProductEntities = orderProductRepository.findByOrderEntityOrderNum(order_num);

            List<Integer> productNums = new ArrayList<>();
            List<Integer> orderCnts = new ArrayList<>();

            for (OrderProductEntity orderProduct : orderProductEntities) {
                System.out.println(orderProduct.toString());
                productNums.add(orderProduct.getProductEntity().getProductNum());
                orderCnts.add(orderProduct.getOrderCnt());
                OrderDTO orderDTO=new OrderDTO();
                orderDTOS.add(OrderDTO.toOrderDTO(order, orderProduct,productNums,orderCnts));
            }
        }
        return orderDTOS;
    }
}
