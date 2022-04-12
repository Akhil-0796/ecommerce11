package com.example.ecommerce.ecommerce.service.ServiceImpl;

import com.example.ecommerce.ecommerce.dto.SupplierDTO;
import com.example.ecommerce.ecommerce.model.Supplier;
import com.example.ecommerce.ecommerce.repository.SupplierRepository;
import com.example.ecommerce.ecommerce.service.SupplierService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DtoMapper dtoMapper;


    @Override
    public SupplierDTO addSupplier(SupplierDTO supplierDTO) {
        if(supplierRepository.findBySupplierName(supplierDTO.getSupplierName())!=null) return null;
        Supplier supplier = dtoMapper.supplierDtoToupplier(supplierDTO);
        supplier.setId(UUID.randomUUID().toString());
        return dtoMapper.SupplierToDTO(supplierRepository.save(supplier));
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public SupplierDTO findById(String supplierId) {
        Optional<Supplier> supplierDb = supplierRepository.findById(supplierId);
        if (supplierDb.isPresent()) return dtoMapper.SupplierToDTO(supplierDb.get());
        return null;
    }

    @Override
    public void deleteById(String supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public void updateSupplier(SupplierDTO supplierDTO) {
        Supplier dbSupplier = supplierRepository.findById(supplierDTO.getId()).get();
        supplierDTO.setId(dbSupplier.getId());
        supplierRepository.save(dtoMapper.supplierDtoToupplier(supplierDTO));
    }
}
