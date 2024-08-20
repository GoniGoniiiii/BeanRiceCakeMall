package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.OrderDTO;
import com.example.beanricecakemall.entity.OrderEntity;
import com.example.beanricecakemall.entity.OrderProductEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.OrderProductRepository;
import com.example.beanricecakemall.repository.OrderRepository;
import com.example.beanricecakemall.repository.ProductRepository;
import com.example.beanricecakemall.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public OrderProductService(OrderProductRepository orderProductRepository, ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void insertOrder(OrderDTO orderDTO) {
        ProductEntity productEntity=null;
        OrderEntity orderEntity=null;
        UserEntity userEntity=null;

        //product
        List<Integer> product_num = orderDTO.getProduct_num();

        for (int product : product_num) {
           productEntity=productRepository.findByProductNum(product);
           productEntity.toString();
        }
        //order
        int order_num=orderDTO.getOrder_num();
        Optional<OrderEntity>  order=orderRepository.findById(order_num);
        if(order.isPresent()){
            orderEntity=order.get();
            orderEntity.toString();
        }

        //user
        int user_num=orderDTO.getUser_num();
        Optional<UserEntity> user = userRepository.findById(user_num);
        if(user.isPresent()) {
            userEntity = user.get();
            userEntity.toString();
        }
        OrderProductEntity orderProductEntity = OrderProductEntity.toSave(orderDTO,orderEntity,productEntity,userEntity);
        orderProductRepository.save(orderProductEntity);
    }
}
