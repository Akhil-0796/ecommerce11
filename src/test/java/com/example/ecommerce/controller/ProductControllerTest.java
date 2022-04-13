package com.example.ecommerce.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@WebMvcTest(value=ProductController.class,useDefaultFilters = false)
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

    @Test
    public void addProduct() {
    }

    @Test
    public void getAllProducts() {
    }

    @Test
    public void deleteProduct() {
    }

    @Test
    public void getProductById() {
    }
}