package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface SupplierService {
    Supplier addSupplier(Supplier supplier);

    List<Supplier> getAllSupplier();

    Optional<Supplier> findById(UUID supplier_id);

    void deleteById(UUID supplier_id);

    void updateSupplier(Supplier supplier);
}
