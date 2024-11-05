package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.DeliveryDTO;
import com.example.beanricecakemall.dto.OrderDTO;
import com.example.beanricecakemall.entity.*;
import com.example.beanricecakemall.repository.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final DeliveryRepository deliveryRepository;

    private final UserRepository userRepository;

    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, DeliveryRepository deliveryRepository, UserRepository userRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, ProductRepository productRepository1) {
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.userRepository = userRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository1;
    }

    public int insertOrder(OrderDTO orderDTO, DeliveryDTO deliveryDTO) {
        if (orderDTO != null && deliveryDTO != null) {
            System.out.println("service단 getUserNum : " + orderDTO.getUser_num());
            UserEntity userEntity = userRepository.findByUserNum(orderDTO.getUser_num());
            OrderEntity orderEntity = OrderEntity.toSave(orderDTO, userEntity);
            orderRepository.save(orderEntity);

            DeliveryEntity deliveryEntity2 = DeliveryEntity.toSave(deliveryDTO, orderEntity);
            deliveryRepository.save(deliveryEntity2);

            List<Integer> productNums=orderDTO.getProduct_num();
            List<Integer> orderCnts=orderDTO.getOrder_cnt();

            for(int i=0; i<productNums.size(); i++){
                int product_num=productNums.get(i);
                int order_cnt=orderCnts.get(i);

                ProductEntity product=productRepository.findByProductNum(product_num);
                int product_cnt=product.getProductCnt() - order_cnt;
                product.setProductCnt(product_cnt);
                productRepository.save(product);
            }

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
            List<Integer> orderDetailNums = new ArrayList<>();
            List<Integer> orderOprices = new ArrayList<>();
            List<String> orderStatuses = new ArrayList<>();
            List<Integer> orderCnts=new ArrayList<>();

            for (OrderProductEntity orderProduct : orderProductEntities) {
                //orderProductEntity 뽑아내서
                productNums.add(orderProduct.getProductEntity().getProductNum());
                orderDetailNums.add(orderProduct.getOrderDetailNum());
                orderOprices.add(orderProduct.getOrderOprice());
                orderStatuses.add(orderProduct.getOrderStatus());
                orderCnts.add(orderProduct.getOrderCnt());

                //orderDTO에 담아서 보내주기
                OrderDTO orderDTO = OrderDTO.toOrderDTO(order, productNums);
                orderDTO.setOrder_detail_num(orderDetailNums);
                orderDTO.setOrder_oprice(orderOprices);
                orderDTO.setOrder_status(orderStatuses);
                orderDTO.setOrder_cnt(orderCnts);

                System.out.println("orderDTO :" +orderDTO);
                orderDTOS.add(orderDTO);
            }

        }
        return orderDTOS;
    }

    public OrderDTO orderDetail(int order_num){

        Optional<OrderEntity> orderEntity= orderRepository.findById(order_num);
        List<OrderProductEntity> orderProductEntity=orderProductRepository.findByOrderEntityOrderNum(order_num);

        List<Integer> productNums = new ArrayList<>();
        List<Integer> orderDetailNums = new ArrayList<>();
        List<Integer> orderOprices = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();
        List<Integer> orderCnts=new ArrayList<>();

        OrderEntity order=new OrderEntity();
        OrderDTO orderDTO=new OrderDTO();

        if(orderEntity.isPresent()){
            order=orderEntity.get();

            for(OrderProductEntity orderProduct: orderProductEntity){
                productNums.add(orderProduct.getProductEntity().getProductNum());
                orderDetailNums.add(orderProduct.getOrderDetailNum());
                orderOprices.add(orderProduct.getOrderOprice());
                orderStatuses.add(orderProduct.getOrderStatus());
                orderCnts.add(orderProduct.getOrderCnt());
            }
            orderDTO=OrderDTO.toOrderDTO(order,productNums);
            orderDTO.setOrder_detail_num(orderDetailNums);
            orderDTO.setOrder_oprice(orderOprices);
            orderDTO.setOrder_status(orderStatuses);
            orderDTO.setOrder_cnt(orderCnts);
            System.out.println("orderDTO : " +orderDTO.toString());
        }
        return orderDTO;
    }

    public Timestamp findOrderDate(int order_num){
        OrderEntity order= orderRepository.findByOrderNum(order_num);
        if(order==null){
            System.out.println("결제일시 없음");
        }else{
            return order.getOrderDate();
        }
        return null;
    }
}
