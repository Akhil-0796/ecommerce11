package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.util.DtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private CategoryRepository categoryRepository;


    @Test
    public void testCategoryById(){
        Category category1 = new Category();
        category1.setName("electronics");
        category1.setCategoryId("1");

        when(categoryRepository.findById("1")).thenReturn(java.util.Optional.of(category1));
        assertEquals("electronics",categoryService.getCategoryById("1").get().getName());
    }

    @Test
    public void testCategoryAdd(){
        Category category1 = new Category();
        category1.setName("electronics");
        category1.setCategoryId("1");

        when(categoryRepository.save(category1)).thenReturn(category1);
        assertEquals("electronics",categoryService.addCategory(category1).getName());
    }
}
