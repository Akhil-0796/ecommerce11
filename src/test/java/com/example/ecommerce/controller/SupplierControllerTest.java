package com.example.ecommerce.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@WebMvcTest(value=SupplierController.class,useDefaultFilters = false)
@ExtendWith(SpringExtension.class)
public class SupplierControllerTest {

    @Test
    public void addSupplier() {
    }

    @Test
    public void getAllSupplier() {
    }

    @Test
    public void deleteSupplier() {
    }

    @Test
    public void updateSupplier() {
    }

    @Test
    public void getSupplierById() {
    }
}