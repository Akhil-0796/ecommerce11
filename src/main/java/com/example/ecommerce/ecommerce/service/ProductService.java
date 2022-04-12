package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.dto.ProductDTO;
import com.example.ecommerce.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    ProductDTO addProduct(ProductDTO product);

    List<ProductDTO> getAllProducts();

    ProductDTO findById(String productId);

    List<Product> findAllProduct(List<String> items);
}
