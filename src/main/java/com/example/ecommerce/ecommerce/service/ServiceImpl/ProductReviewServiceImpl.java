package com.example.ecommerce.ecommerce.service.ServiceImpl;

import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.repository.ProductRepository;
import com.example.ecommerce.ecommerce.repository.ProductReviewRepository;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import com.example.ecommerce.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public ProductReviewDTO addReview(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = dtoMapper.productReviewToProductReviewDto(productReviewDTO);
        if(productReviewDTO.getId().isEmpty())
        productReview.setId(UUID.randomUUID().toString());
        productReview.setUserId(UUID.randomUUID().toString());
        productReview.setProductId(productReviewDTO.getProductId());
        return dtoMapper.productReviewToProductReviewDto(productReviewRepository.save(productReview));
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

    @Override
    public boolean findProductById(String productId) {
        if(productRepository.findById(productId).isPresent()) return true;
        else
            return false;
    }

    @Override
    public List<ProductReviewDTO> getReviewsByProductId(String productId) {
        List<ProductReview> response  = productReviewRepository.findAllByProductId(productId);
        return new ArrayList<>();
    }

    @Override
    public List<ProductReviewDTO> findAllReviews() {
        List<ProductReviewDTO> response  = new ArrayList<>();
        for(ProductReview productReview:productReviewRepository.findAll()){
            response.add(dtoMapper.productReviewToProductReviewDto(productReview));
        }
        return response;
    }
}
