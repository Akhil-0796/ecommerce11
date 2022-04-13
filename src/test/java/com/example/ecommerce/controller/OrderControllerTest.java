package com.example.ecommerce.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@WebMvcTest(value=OrderController.class,useDefaultFilters = false)
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

    @Test
    public void validateOrder() {
    }

    @Test
    public void addOrder() {
    }

    @Test
    public void getOrderDetailsByOrderId() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void getAllOrder() {
    }
}