package com.example.ecommerce.ecommerce.repository;

import com.example.ecommerce.ecommerce.model.ProductReview;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductReviewRepository extends CassandraRepository<ProductReview, String> {
}
