package com.example.beanricecakemall.entity;

import com.example.beanricecakemall.dto.CartDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.parameters.P;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="cart_num")
    private int cartNum;

    @Column(name="cart_date")
    @CreationTimestamp
    private Timestamp cartDate;

    @Column(nullable = false , name="cart_cnt")
    private int cartCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_num")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_num")
    private UserEntity userEntity;
    public static CartEntity toSaveEntity(CartDTO cartDTO){
        CartEntity cartEntity=new CartEntity();
        cartEntity.setCartCnt(cartDTO.getCart_cnt());
        return cartEntity;
    }

}
