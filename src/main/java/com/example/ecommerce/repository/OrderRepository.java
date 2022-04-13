package com.example.ecommerce.repository;

import com.example.ecommerce.model.Order;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CassandraRepository<Order, String> {
}
