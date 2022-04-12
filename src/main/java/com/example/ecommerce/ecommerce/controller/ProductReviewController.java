package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/product-review")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("/add")
    public ProductReviewDTO addReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.addReview(productReviewDTO);
    }

    @PutMapping("/update")
    public ProductReviewDTO updateReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.update(productReviewDTO);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") String reviewId){
        boolean response = productReviewService.deleteReview(reviewId);
        if(response) return new ResponseEntity<>("Deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Not Present",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{reviewId}")
    public ProductReviewDTO getProductReview(@PathVariable("reviewId") String reviewId){
        return productReviewService.getReviewById(reviewId);
    }

    @GetMapping("/reviews-by-product/{productId}")
    public ResponseEntity<?> reviewsByProduct(@PathVariable("productId") String productId){
        if(!productReviewService.findProductById(productId)) return new ResponseEntity<>("No Product with given Id",HttpStatus.NOT_FOUND);
        List<ProductReviewDTO> response  =productReviewService.getReviewsByProductId(productId);
        if(response==null || response.isEmpty()) return new ResponseEntity<>("No review found for the given product.",HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allReviews(){
        List<ProductReviewDTO> response = productReviewService.findAllReviews();
        if(response==null || response.isEmpty()) return new ResponseEntity<>("No Review found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
