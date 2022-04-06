package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-review")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("/add")
    public ProductReview addReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.addReview(productReviewDTO);
    }

    @PutMapping("/update")
    public ProductReviewDTO updateReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.update(productReviewDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId){
        boolean response = productReviewService.deleteReview(reviewId);
        if(response) return new ResponseEntity<>("Deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Not Present",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ProductReviewDTO getProductReview(@PathVariable String reviewId){
        return productReviewService.getReviewById(reviewId);
    }


}
