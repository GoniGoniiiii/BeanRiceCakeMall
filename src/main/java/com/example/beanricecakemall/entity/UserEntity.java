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

    @Column(unique = true,name="user_id")
    private String userId;

    @Column(name="user_pw")
    private String userPw;

    @Column(nullable = false,name="user_name")
    private String userName;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="user_tel")
    private String userTel;

    @Column(name="user_birth")
    private String userBirth;

    @Column(length = 1,name="user_registration")
    private String userRegistration;

    @Column(updatable = false,name="user_joindate")
    @CreationTimestamp
    private Timestamp userJoindate;

    @Column(columnDefinition = "DEFAULT 0",name="user_point")
    private int userPoint;

    @Column(name="user_role")
    private String userRole;

    @Column(name="user_zipcode")
    private int userZipcode;

    @Column(name="user_addr")
    private String userAddr;

    @Column(name="user_addrdetail")
    private String userAddrdetail;


    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList=new ArrayList<>();

    @OneToMany(mappedBy ="userEntity" ,cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "userEntity" , cascade =CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    public List<CartEntity> cartEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    public List<OrderProductEntity> orderProductEntityList=new ArrayList<>();


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
        userEntity.setUserZipcode(userDTO.getUser_zipcode());
        userEntity.setUserAddr(userDTO.getUser_addr());
        userEntity.setUserAddrdetail(userDTO.getUser_addrDetail());
        return userEntity;
    }

    public static UserEntity toUpdateEntity(UserDTO userDTO){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserNum(userDTO.getUser_num());
        userEntity.setUserId(userDTO.getUser_id());
        userEntity.setUserPw(userDTO.getUser_pw());
        userEntity.setUserName(userDTO.getUser_name());
        userEntity.setUserEmail(userDTO.getUser_email());
        userEntity.setUserBirth(userDTO.getUser_birth());
        userEntity.setUserPoint(userDTO.getUser_point());
        userEntity.setUserRole(userDTO.getUser_role());
        userEntity.setUserRegistration(userDTO.getUser_registraion());
        userEntity.setUserTel(userDTO.getUser_tel());
        userEntity.setUserZipcode(userDTO.getUser_zipcode());
        userEntity.setUserAddr(userDTO.getUser_addr());
        userEntity.setUserAddrdetail(userDTO.getUser_addrDetail());
        return userEntity;
    }
}

