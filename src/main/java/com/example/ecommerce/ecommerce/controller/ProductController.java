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

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product){
        product.setProduct_id(UUID.randomUUID());
        return productService.addProduct(product);
    }

    @GetMapping("/all-products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID product_id){
        if(!productRepository.findById(product_id).isPresent()) return new ResponseEntity<>("Product Not Found ", HttpStatus.NOT_FOUND);
        productRepository.deleteById(product_id);
        return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
    }

    @GetMapping("/get-product-by-id/{id}")
    public Product getProductById(@PathVariable UUID product_id){
        if(!productRepository.findById(product_id).isPresent()) return null;
        return productService.findById(product_id);
    }
}
