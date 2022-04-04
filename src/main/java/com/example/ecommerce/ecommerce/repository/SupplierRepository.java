package com.example.ecommerce.ecommerce.repository;

import com.example.ecommerce.ecommerce.model.Supplier;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface SupplierRepository extends CassandraRepository<Supplier, UUID> {
}
