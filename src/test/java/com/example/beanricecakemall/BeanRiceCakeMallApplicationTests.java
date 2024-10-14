package com.example.beanricecakemall;

import com.example.beanricecakemall.entity.FileEntity;
import com.example.beanricecakemall.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BeanRiceCakeMallApplicationTests {

    @Autowired
    private FileRepository fileRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindByProductEntityProductNum(){
        int productNum=18;
        List<FileEntity> fileEntity=fileRepository.findByProductEntityProductNum(productNum);
        assertNotNull(fileEntity);

        for(FileEntity fileEntity1:fileEntity){
            System.out.println(fileEntity1.getFileNum());
            System.out.println(fileEntity1.getFileUrl());
            System.out.println(fileEntity1.getProductEntity().getProductNum());
        }
    }
}
