package com.example.beanricecakemall.dto;

import com.example.beanricecakemall.entity.CartEntity;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class CartDTO{

    private int cart_num;

    private int cart_cnt;

    private Timestamp cart_date;

    private int user_num;

    private int product_num;

    private String product_img;


    public static CartDTO toCartDTO(CartEntity cartEntity){
        CartDTO cartDTO=new CartDTO();
        cartDTO.setCart_num(cartEntity.getCartNum());
        cartDTO.setCart_cnt(cartEntity.getCartCnt());
        cartDTO.setCart_date(cartEntity.getCartDate());
        cartDTO.setUser_num(cartEntity.getUserEntity().getUserNum());
        cartDTO.setProduct_num(cartEntity.getProductEntity().getProductNum());
        cartDTO.setProduct_img(cartEntity.getProductEntity().getProductImg());
        return cartDTO;
    }
}
