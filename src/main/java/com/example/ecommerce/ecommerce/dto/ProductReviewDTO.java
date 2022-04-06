package com.example.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDTO {
    private String id;
    private String review;
    private String userId;
    private String productId;
}
