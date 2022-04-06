package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.model.Supplier;
import com.example.ecommerce.ecommerce.repository.SupplierRepository;
import com.example.ecommerce.ecommerce.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public Supplier addSupplier(Supplier supplier) {
        supplier.setId(UUID.randomUUID().toString());
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(String supplierId) {
        return supplierRepository.findById(supplierId);
    }

    @Override
    public void deleteById(String supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        Supplier dbSupplier = supplierRepository.findById(supplier.getId()).get();
        supplier.setId(dbSupplier.getId());
        supplierRepository.save(supplier);
    }
}
