package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.repository.ProductReviewRepository;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import com.example.ecommerce.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public ProductReview addReview(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = dtoMapper.productReviewToProductReviewDto(productReviewDTO);
        productReview.setId(UUID.randomUUID().toString());
        return productReviewRepository.save(productReview);
    }

    @Override
    public ProductReviewDTO update(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = productReviewRepository.findById(productReviewDTO.getId()).get();
        if(UserUtil.getCurrentUser().getId().equals(productReviewDTO.getUserId())){
            ProductReview productReviewDb =  productReviewRepository.save(dtoMapper.productReviewToProductReviewDto(productReviewDTO));
            return dtoMapper.productReviewToProductReviewDto(productReviewDb);
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteReview(String reviewId) {
        Optional<ProductReview> productReviewDb = productReviewRepository.findById(reviewId);
        if(productReviewDb.isPresent()){
            productReviewRepository.deleteById(productReviewDb.get().getId());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ProductReviewDTO getReviewById(String reviewId) {
        Optional<ProductReview> productReviewDb = productReviewRepository.findById(reviewId);
        if(productReviewDb.isPresent()){
            return dtoMapper.productReviewToProductReviewDto(productReviewDb.get());
        }else{
            return null;
        }
    }
}
