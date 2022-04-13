package com.example.ecommerce.repository;

import com.example.ecommerce.model.Supplier;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CassandraRepository<Supplier, String> {
    @AllowFiltering
    Supplier findBySupplierName(String supplierName);
}
