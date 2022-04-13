package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.service.ProductReviewService;
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

    /**
     * will add the review for a product
     * @param productReviewDTO
     * @return ProductReviewDTO
     */
    @PostMapping("/add")
    public ProductReviewDTO addReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.addReview(productReviewDTO);
    }

    /**
     * will update the review
     * @param productReviewDTO
     * @return updated ProductReviewDTO
     */
    @PutMapping("/update")
    public ProductReviewDTO updateReview(@RequestBody ProductReviewDTO productReviewDTO){
        return productReviewService.update(productReviewDTO);
    }

    /**
     * will remove review from system based on id
     * @param reviewId
     * @return response message
     */
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") String reviewId){
        boolean response = productReviewService.deleteReview(reviewId);
        if(response) return new ResponseEntity<>("Deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Not Present",HttpStatus.NOT_FOUND);
    }

    /**
     * will return review based on id
     * @param reviewId
     * @return ProductReviewDTO
     */
    @GetMapping("/get/{reviewId}")
    public ProductReviewDTO getProductReview(@PathVariable("reviewId") String reviewId){
        return productReviewService.getReviewById(reviewId);
    }

    /**
     * will return review based on product id
     * @param productId
     * @return List of ProductReviewDTO
     */
    @GetMapping("/reviews-by-product/{productId}")
    public ResponseEntity<?> reviewsByProduct(@PathVariable("productId") String productId){
        if(!productReviewService.findProductById(productId)) return new ResponseEntity<>("No Product with given Id",HttpStatus.NOT_FOUND);
        List<ProductReviewDTO> response  =productReviewService.getReviewsByProductId(productId);
        if(response==null || response.isEmpty()) return new ResponseEntity<>("No review found for the given product.",HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * will return all review
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<?> allReviews(){
        List<ProductReviewDTO> response = productReviewService.findAllReviews();
        if(response==null || response.isEmpty()) return new ResponseEntity<>("No Review found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
