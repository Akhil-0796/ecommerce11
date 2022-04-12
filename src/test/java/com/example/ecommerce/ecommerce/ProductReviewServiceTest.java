package com.example.ecommerce.ecommerce;



import com.example.ecommerce.ecommerce.model.Product;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.repository.ProductRepository;
import com.example.ecommerce.ecommerce.repository.ProductReviewRepository;
import com.example.ecommerce.ecommerce.service.ProductReviewService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductReviewServiceTest {

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private ProductReviewRepository productReviewRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testAllReviews(){
        ProductReview productReview1= new ProductReview();
        productReview1.setId("12345");
        productReview1.setReview("awesome product");
        productReview1.setProductId("p123");
        productReview1.setUserId("1234");

        ProductReview productReview2= new ProductReview();
        productReview2.setId("12345");
        productReview2.setReview("awesome product");
        productReview2.setProductId("p123");
        productReview2.setUserId("1234");

        when(productReviewRepository.findAll()).thenReturn(Stream.of(productReview1,productReview2).collect(Collectors.toList()));
        assertEquals(2, productReviewService.findAllReviews().size());
    }

    @Test
    public void testReviewById(){
        ProductReview productReview1= new ProductReview();
        productReview1.setId("12345");
        productReview1.setReview("awesome product");
        productReview1.setProductId("p123");
        productReview1.setUserId("1234");
        when(productReviewRepository.findById("12345")).thenReturn(java.util.Optional.of(productReview1));
        assertEquals("p123",productReviewService.getReviewById("12345").getProductId());
    }
}
