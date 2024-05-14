package com.example.beanricecakemall.entity;

import com.example.beanricecakemall.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_num")
    private int userNum;

    @Column(unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userName;

    @Column
    private String userEmail;

    @Column
    private String userTel;

    @Column(nullable = false)
    private String userBirth;

    @Column(length = 1,columnDefinition = "DEFAULT 'N'")
    private String userRegistration;

    @Column(updatable = true)
    @CreationTimestamp
    private Timestamp userJoindate;

    @Column(columnDefinition = "DEFAULT 0")
    private int userPoint;

    @Column(columnDefinition = "DEFAULT 'ROLE_USER'")
    private String userRole;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList=new ArrayList<>();

    @OneToMany(mappedBy ="userEntity" ,cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList=new ArrayList<>();

    public static UserEntity toSaveEntity(UserDTO userDTO){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(userDTO.getUser_id());
        userEntity.setUserPw(userDTO.getUser_pw());
        userEntity.setUserName(userDTO.getUser_name());
        userEntity.setUserEmail(userDTO.getUser_email());
        userEntity.setUserBirth(userDTO.getUser_birth());
        userEntity.setUserPoint(userDTO.getUser_point());
        userEntity.setUserRole(userDTO.getUser_role());
        userEntity.setUserRegistration(userDTO.getUser_registraion());
        userEntity.setUserTel(userDTO.getUser_tel());
        return userEntity;
    }
}

