package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.CartDTO;
import com.example.beanricecakemall.entity.CartEntity;
import com.example.beanricecakemall.entity.ProductEntity;
import com.example.beanricecakemall.entity.UserEntity;
import com.example.beanricecakemall.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        productEntity.setProductImg(cartDTO.getProduct_img());
        userEntity.setUserNum(cartDTO.getUser_num());

        cart.setProductEntity(productEntity);
        cart.setUserEntity(userEntity);

        String result = null;
        if (cart != null) {
            boolean exist = cartRepository.existsByProductEntityProductNum(cartDTO.getProduct_num());
            if (exist) {
                int addCnt=cartDTO.getCart_cnt();
                System.out.println("추가 되는 수량 : " +addCnt);
                CartEntity cartEntity= cartRepository.findCartCntByProductEntityProductNum(cartDTO.getProduct_num());
                int existCnt=cartEntity.getCartCnt();
                System.out.println("기존  수량 : " +existCnt);
                cart.setCartCnt(existCnt+addCnt);
                cart.setCartNum(cartEntity.getCartNum());
                cartRepository.save(cart);
                result="기존 장바구니에 있던 상품의 수량이 변경되었습니다!";
            } else {
                cartRepository.save(cart);
                result = "장바구니에 추가되었습니다!";
            }
        } else {
            result = "장바구니에 추가되지 않았음";
        }
        return result;
    }

    public List<CartDTO> cartList(int user_num) {
        List<CartEntity> cartEntityList = cartRepository.findAllByUserEntityUserNum(user_num);
        List<CartDTO> cartDTOS = new ArrayList<>();

        if (cartEntityList != null) {
            for (CartEntity cartEntity : cartEntityList) {
                cartDTOS.add(CartDTO.toCartDTO(cartEntity));
            }
        } else {
            System.out.println("조회된 내용이 없습니다.");
        }
        return cartDTOS;
    }

    public void update(CartDTO cartDTO) {
        CartEntity cartEntity = CartEntity.toUpdateEntity(cartDTO);
        ProductEntity productEntity = new ProductEntity();
        UserEntity userEntity = new UserEntity();

        userEntity.setUserNum(cartDTO.getUser_num());
        productEntity.setProductImg(cartDTO.getProduct_img());
        productEntity.setProductNum(cartDTO.getProduct_num());

        cartEntity.setUserEntity(userEntity);
        cartEntity.setProductEntity(productEntity);
        cartRepository.save(cartEntity);
    }

    @Transactional
    public void delete(int product_num, int user_num) {
        cartRepository.deleteByProductEntityProductNumAndUserEntityUserNum(product_num, user_num);
    }

}