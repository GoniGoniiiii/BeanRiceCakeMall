package com.example.beanricecakemall.service;

import com.example.beanricecakemall.dto.CategoryDTO;
import com.example.beanricecakemall.entity.CategoryEntity;
import com.example.beanricecakemall.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String categoryName(int category_num) {
            Optional<CategoryEntity> categoryEntities = categoryRepository.findById(category_num);

            if(categoryEntities.isPresent()) {
                CategoryEntity category = categoryEntities.get();
                return category.getCategoryName();
            }
            return null;
        }
}