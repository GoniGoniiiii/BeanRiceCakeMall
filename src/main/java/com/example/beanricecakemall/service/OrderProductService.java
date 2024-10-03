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
        List<ProductEntity> productEntities = new ArrayList<>();
        OrderEntity orderEntity = null;
        UserEntity userEntity = null;

        //product
        List<Integer> order_cnt = orderDTO.getOrder_cnt();

        // product_num을 기반으로 각 상품 엔티티를 가져와서 리스트에 추가
        for (int productNum : orderDTO.getProduct_num()) {
            ProductEntity productEntity = productRepository.findByProductNum(productNum);
            if (productEntity != null) {
                productEntities.add(productEntity);
            }
        }

        //order
        int order_num = orderDTO.getOrder_num();
        Optional<OrderEntity> order = orderRepository.findById(order_num);
        if (order.isPresent()) {
            orderEntity = order.get();
        }

        //user
        int user_num = orderDTO.getUser_num();
        Optional<UserEntity> user = userRepository.findById(user_num);
        if (user.isPresent()) {
            userEntity = user.get();
        }

        for (int i = 0; i < orderDTO.getProduct_num().size(); i++) {
            int productNum = orderDTO.getProduct_num().get(i);
            int orderCnt = orderDTO.getOrder_cnt().get(i);
            int orderOprice=orderDTO.getOrder_oprice().get(i);

            ProductEntity productEntity = productEntities.get(i);

            OrderProductEntity orderProductEntity = OrderProductEntity.toSave(orderDTO, orderEntity, productEntity, userEntity);
            orderProductEntity.setOrderOprice(orderOprice);
            orderProductEntity.setOrderCnt(orderCnt);
            orderProductRepository.save(orderProductEntity);
        }

    }


    public List<OrderDTO> findOrderProductList(int order_num){
        List<OrderProductEntity> orderProductEntities=orderProductRepository.findByOrderEntityOrderNum(order_num);
        List<OrderDTO> orderDTOS=new ArrayList<>();
        List<Integer> orderCnts=new ArrayList<>();
        List<Integer> orderOprices=new ArrayList<>();


        for(OrderProductEntity orderProduct: orderProductEntities){
            orderCnts.add(orderProduct.getOrderCnt());
            orderOprices.add(orderProduct.getOrderOprice());

            OrderDTO orderDTO=new OrderDTO();
            orderDTO.setOrder_cnt(orderCnts);
            orderDTO.setOrder_oprice(orderOprices);
            orderDTO.setOrder_num(order_num);

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
