package com.example.beanricecakemall.repository;

import com.example.beanricecakemall.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {
}
