package com.example.beanricecakemall.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ReviewDTO {

    private int review_num;

    private String review_title;

    private String review_content;

    private Timestamp review_rdate;

    private int user_num;

    private int product_num;

    private String user_id;
}
