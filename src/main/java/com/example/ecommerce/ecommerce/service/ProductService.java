package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product findById(UUID product_id);
}
