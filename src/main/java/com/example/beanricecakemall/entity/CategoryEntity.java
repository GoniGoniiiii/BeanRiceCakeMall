package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int category_num;

    @Column(nullable = false,unique = true)
    private String category_name;

    @OneToMany(mappedBy = "categoryEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList=new ArrayList<>();


}
