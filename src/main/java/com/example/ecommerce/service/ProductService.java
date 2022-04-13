package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Product;

import java.util.List;


public interface ProductService {
    ProductDTO addProduct(ProductDTO product);

    List<ProductDTO> getAllProducts();

    ProductDTO findById(String productId);

    List<Product> findAllProduct(List<String> items);
}
