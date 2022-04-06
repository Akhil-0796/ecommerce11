package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CategoryService {
    Category addCategory(Category category);

    Optional<Category> getCategoryById(String categoryId);

    Category update(Category category);

    boolean deleteCategory(String categoryId);
}
