package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;

import java.util.Optional;


public interface CategoryService {
    Category addCategory(Category category);

    Optional<Category> getCategoryById(String categoryId);

    Category update(Category category);

    boolean deleteCategory(String categoryId);
}
