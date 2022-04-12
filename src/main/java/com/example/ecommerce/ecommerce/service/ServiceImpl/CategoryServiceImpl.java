package com.example.ecommerce.ecommerce.service.ServiceImpl;

import com.example.ecommerce.ecommerce.model.Category;
import com.example.ecommerce.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        Integer categoryCount = categoryRepository.findAll().size();
        category.setCategoryId(Integer.toString(categoryCount+1));
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteCategory(String categoryId) {
        Optional<Category> categoryDb = categoryRepository.findById(categoryId);
        if(categoryDb.isPresent()){
            categoryRepository.delete(categoryDb.get());
            return true;
        }
        return false;
    }
}
