package com.example.ecommerce.ecommerce;

import com.example.ecommerce.ecommerce.dto.OrderDTO;
import com.example.ecommerce.ecommerce.model.Category;
import com.example.ecommerce.ecommerce.model.Order;
import com.example.ecommerce.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.ecommerce.repository.OrderRepository;
import com.example.ecommerce.ecommerce.service.CategoryService;
import com.example.ecommerce.ecommerce.service.OrderService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
