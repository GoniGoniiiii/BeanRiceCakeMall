package com.example.beanricecakemall.entity;

import com.example.beanricecakemall.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_num;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String product_content;

    @Column(nullable = false)
    private int product_oprice;

    @Column(nullable = false)
    private int product_sprice;

    @Column
    @CreationTimestamp
    private Timestamp product_rdate;

    @Column(nullable = false)
    private int product_cnt;

    @Column(length=1)
    private String product_delete;

    @Column
    private String product_img;

    @Column
    private int product_deliveryfee;

    @Column(nullable = false)
    private int product_rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_num",insertable = true,updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_num", insertable = true,updatable = false)
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_num",insertable = true,updatable = false)
    private CartEntity cartEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_num",updatable = false)
    private OrderEntity orderEntity;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade=CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList=new ArrayList<>();

    public static ProductEntity toProductEntity(ProductDTO productDTO){
        ProductEntity productEntity=new ProductEntity();
        productEntity.setProduct_name(productDTO.getProduct_name());
        productEntity.setProduct_content(productDTO.getProduct_content());
        productEntity.setProduct_oprice(productDTO.getProduct_oprice());
        productEntity.setProduct_sprice(productDTO.getProduct_sprice());
        productEntity.setProduct_cnt(productDTO.getProduct_cnt());
        productEntity.setProduct_delete("N");
        productEntity.setProduct_img(productEntity.getProduct_img());
        productEntity.setProduct_deliveryfee(productDTO.getProduct_deleveryfee());
        productEntity.setProduct_rate(productDTO.getProduct_rate());
        return productEntity;
    }

}
