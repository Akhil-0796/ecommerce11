package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.dto.SupplierDTO;
import com.example.ecommerce.ecommerce.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SupplierService {
    SupplierDTO addSupplier(SupplierDTO supplier);

    List<Supplier> getAllSupplier();

    SupplierDTO findById(String supplierId);

    void deleteById(String supplierId);

    void updateSupplier(SupplierDTO supplierDTO);
}
