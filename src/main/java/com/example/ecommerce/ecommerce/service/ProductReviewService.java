package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import org.springframework.stereotype.Service;

@Service
public interface ProductReviewService {
    ProductReview addReview(ProductReviewDTO productReviewDTO);
}
