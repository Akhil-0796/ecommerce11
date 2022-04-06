package com.example.ecommerce.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class ProductReview {

    @PrimaryKey
    private String id;

    private String review;
    @Column(value = "user_id")
    private String userId;

    @Column(value = "product_id")
    private String productId;

}
