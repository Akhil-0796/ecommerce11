package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.model.Supplier;
import com.example.ecommerce.ecommerce.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/add")
    public Supplier addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @GetMapping("/get-all")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable String supplierId){
        if(!supplierService.findById(supplierId).isPresent()) return new ResponseEntity<>("Supplier Not Found", HttpStatus.NOT_FOUND);
        supplierService.deleteById(supplierId);
        return new ResponseEntity<>("Supplier Deleted",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSupplier(@RequestBody Supplier supplier){
        if(!supplierService.findById(supplier.getId()).isPresent()) return new ResponseEntity<>("No Supplier Found",HttpStatus.NOT_FOUND);
        supplierService.updateSupplier(supplier);
        return new ResponseEntity<>("Supplier Updated",HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public Supplier getSupplierById(@PathVariable String supplierId){
        return supplierService.findById(supplierId).get();
    }
}
