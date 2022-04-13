package com.example.ecommerce.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@WebMvcTest(value=ProductReviewController.class,useDefaultFilters = false)
@ExtendWith(SpringExtension.class)
public class ProductReviewControllerTest {

    @Test
    public void addReview() {
    }

    @Test
    public void updateReview() {
    }

    @Test
    public void deleteReview() {
    }

    @Test
    public void getProductReview() {
    }

    @Test
    public void reviewsByProduct() {
    }

    @Test
    public void allReviews() {
    }
}