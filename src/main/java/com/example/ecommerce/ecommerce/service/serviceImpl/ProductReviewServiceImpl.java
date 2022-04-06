package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.repository.ProductReviewRepository;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public ProductReview addReview(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = dtoMapper.productReviewDtoToProductReview(productReviewDTO);
        productReview.setId(UUID.randomUUID().toString());
        return productReviewRepository.save(productReview);
    }
}
