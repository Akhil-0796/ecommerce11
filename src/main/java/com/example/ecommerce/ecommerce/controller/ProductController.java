package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.model.Product;
import com.example.ecommerce.ecommerce.repository.ProductRepository;
import com.example.ecommerce.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        product.setProductId(UUID.randomUUID().toString());
        return productService.addProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId){
        if(!productRepository.findById(productId).isPresent()) return new ResponseEntity<>("Product Not Found ", HttpStatus.NOT_FOUND);
        productRepository.deleteById(productId);
        return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Product getProductById(@PathVariable String productId){
        if(!productRepository.findById(productId).isPresent()) return null;
        return productService.findById(productId);
    }
}
