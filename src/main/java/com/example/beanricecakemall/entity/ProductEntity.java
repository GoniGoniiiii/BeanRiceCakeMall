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
    @Column(name="product_num")
    private int productNum;

    @Column(nullable = false,name="product_name")
    private String productName;

    @Column(nullable = false,name="product_content")
    private String productContent;

    @Column(nullable = false, name="product_info")
    private String productInfo;

    @Column(nullable = false,name="product_oprice")
    private int productOprice;

    @Column(nullable = false,name="product_sprice")
    private int productSprice;

    @Column(updatable =false, name="product_rdate")
    @CreationTimestamp
    private Timestamp productRdate;

    @Column(nullable = false,name="product_cnt")
    private int productCnt;

    @Column(length=1, name="product_delete")
    private String productDelete;

    @Column(name="product_img")
    private String productImg;

    @Column(name="product_deliveryfee")
    private int productDeliveryfee;

    @Column(nullable = false,name="product_rate")
    private int productRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_num", insertable = true)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CartEntity> cartEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade=CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderProductEntity> orderProductEntityList=new ArrayList<>();


    public static ProductEntity toProductEntity(ProductDTO productDTO,CategoryEntity categoryEntity){
        ProductEntity productEntity=new ProductEntity();
        productEntity.setProductName(productDTO.getProduct_name());
        productEntity.setProductContent(productDTO.getProduct_content());
        productEntity.setProductInfo(productDTO.getProduct_info());
        productEntity.setProductOprice(productDTO.getProduct_oprice());
        productEntity.setProductSprice(productDTO.getProduct_sprice());
        productEntity.setProductCnt(productDTO.getProduct_cnt());
        productEntity.setProductDelete("N");
        productEntity.setProductImg(productDTO.getProduct_img());
        productEntity.setProductDeliveryfee(productDTO.getProduct_deliveryfee());
        productEntity.setProductRate(productDTO.getProduct_rate());
        productEntity.setCategoryEntity(categoryEntity);
        return productEntity;
    }

    public static ProductEntity updateEntity(ProductDTO productDTO, CategoryEntity categoryEntity){
        ProductEntity productEntity=new ProductEntity();
        productEntity.setProductNum(productDTO.getProduct_num());
        productEntity.setProductName(productDTO.getProduct_name());
        productEntity.setProductContent(productDTO.getProduct_content());
        productEntity.setProductInfo(productDTO.getProduct_info());
        productEntity.setProductOprice(productDTO.getProduct_oprice());
        productEntity.setProductSprice(productDTO.getProduct_sprice());
        productEntity.setProductCnt(productDTO.getProduct_cnt());
        productEntity.setProductDelete("N");
        productEntity.setProductImg(productDTO.getProduct_img());
        productEntity.setProductDeliveryfee(productDTO.getProduct_deliveryfee());
        productEntity.setProductRate(productDTO.getProduct_rate());
        productEntity.setCategoryEntity(categoryEntity);
        return productEntity;
    }
}
