package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    /**
     * adding a product
     * @param product
     * @return ProductDTO
     */
    @PostMapping("admin/product/add")
    public ProductDTO addProduct(@RequestBody ProductDTO product){
        return productService.addProduct(product);
    }

    /**
     * getting all products available in the system
     * @return list of Product
     */
    @GetMapping("all")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    /**
     * removing a product from system
     * @param productId
     * @return response message
     */
    @DeleteMapping("admin/product/delete-by-id/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId){
        if(!productRepository.findById(productId).isPresent()) return new ResponseEntity<>("Product Not Found ", HttpStatus.NOT_FOUND);
        productRepository.deleteById(productId);
        return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
    }

    /**
     * return product based on id
     * @param productId
     * @return
     */
    @GetMapping("get-by-id/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") String productId){
        ProductDTO response  = productService.findById(productId);
        if(response==null) return new ResponseEntity<>("No Product found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}
