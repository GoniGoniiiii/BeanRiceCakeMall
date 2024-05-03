package com.example.beanricecakemall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="user")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_num;

    @Column(unique = true)
    private String user_id;

    @Column(nullable = false)
    private String user_pw;

    @Column
    private String user_email;

    @Column(nullable = false)
    private String user_birth;

    @Column(length = 1)
    private String user_registration;

    @Column(updatable = true)
    @CreationTimestamp
    private Timestamp user_joindate;

    @Column
    private String user_point;

}
