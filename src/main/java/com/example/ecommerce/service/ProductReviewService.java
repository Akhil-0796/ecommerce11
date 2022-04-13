package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductReviewDTO;

import java.util.List;


public interface ProductReviewService {
    ProductReviewDTO addReview(ProductReviewDTO productReviewDTO);

    ProductReviewDTO update(ProductReviewDTO productReviewDTO);

    boolean deleteReview(String reviewId);

    ProductReviewDTO getReviewById(String reviewId);

    boolean findProductById(String productId);

    List<ProductReviewDTO> getReviewsByProductId(String productId);

    List<ProductReviewDTO> findAllReviews();
}
