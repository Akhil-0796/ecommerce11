package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.model.Product;
import com.example.ecommerce.ecommerce.repository.ProductRepository;
import com.example.ecommerce.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID product_id) {
        return productRepository.findById(product_id).get();
    }
}
