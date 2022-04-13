package com.example.ecommerce.repository;

import com.example.ecommerce.model.ProductReview;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends CassandraRepository<ProductReview, String> {
    List<ProductReview> findAllByProductId(String productId);
}
