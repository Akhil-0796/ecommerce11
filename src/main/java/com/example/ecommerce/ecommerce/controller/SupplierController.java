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

    @PostMapping("/add-supplier")
    public Supplier addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @GetMapping("/get-all-supplier")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    @DeleteMapping("/delete-supplier-by-id/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable UUID supplier_id){
        if(!supplierService.findById(supplier_id).isPresent()) return new ResponseEntity<>("Supplier Not Found", HttpStatus.NOT_FOUND);
        supplierService.deleteById(supplier_id);
        return new ResponseEntity<>("Supplier Deleted",HttpStatus.OK);
    }

    @PutMapping("/update-supplier")
    public ResponseEntity<?> updateSupplier(@RequestBody Supplier supplier){
        if(!supplierService.findById(supplier.getId()).isPresent()) return new ResponseEntity<>("No Supplier Found",HttpStatus.NOT_FOUND);
        supplierService.updateSupplier(supplier);
        return new ResponseEntity<>("Supplier Updated",HttpStatus.OK);
    }

    @GetMapping("/get-supplier-by-id/{id}")
    public Supplier getSupplierById(@PathVariable UUID supplier_id){
        return supplierService.findById(supplier_id).get();
    }
}
