package com.example.ecommerce.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@WebMvcTest(value=CategoryController.class,useDefaultFilters = false)
@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

    @Test
    public void addCategory() {
    }

    @Test
    public void getCategory() {
    }

    @Test
    public void updateCategory() {
    }

    @Test
    public void deleteCategory() {
    }
}