package com.example.ecommerce.service;

import com.example.ecommerce.dto.SupplierDTO;
import com.example.ecommerce.model.Supplier;

import java.util.List;


public interface SupplierService {
    SupplierDTO addSupplier(SupplierDTO supplier);

    List<Supplier> getAllSupplier();

    SupplierDTO findById(String supplierId);

    void deleteById(String supplierId);

    void updateSupplier(SupplierDTO supplierDTO);
}
