package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-review")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("/add")
    public ProductReview addReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.addReview(productReviewDTO);
    }
}
