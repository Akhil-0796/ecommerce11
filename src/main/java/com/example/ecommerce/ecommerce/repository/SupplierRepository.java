package com.example.ecommerce.ecommerce.repository;

import com.example.ecommerce.ecommerce.model.Supplier;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends CassandraRepository<Supplier, String> {
    @AllowFiltering
    Supplier findBySupplierName(String supplierName);
}
