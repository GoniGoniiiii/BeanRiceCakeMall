package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.entity.CartEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public String insert(CartDTO cartDTO) {
        CartEntity cart = CartEntity.toSaveEntity(cartDTO);
        ProductEntity productEntity = new ProductEntity();
        UserEntity userEntity = new UserEntity();

        productEntity.setProductNum(cartDTO.getProduct_num());
        userEntity.setUserNum(cartDTO.getUser_num());

        cart.setProductEntity(productEntity);
        cart.setUserEntity(userEntity);

        String result = null;
        if (cart != null) {
            cartRepository.save(cart);
            result = "장바구니에 추가되었습니다!";
        } else {
            result = "장바구니에 추가되지 않았음";
        }
        return result;
    }
}