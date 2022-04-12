package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.dto.SupplierDTO;
import com.example.ecommerce.ecommerce.model.Supplier;
import com.example.ecommerce.ecommerce.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/add")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO supplier){
        SupplierDTO response =  supplierService.addSupplier(supplier);
        if (response==null) return new ResponseEntity<>("Supplier Already Present",HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    @DeleteMapping("/delete-by-id/{supplierId}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("supplierId") String supplierId){
        if(supplierService.findById(supplierId)==null) return new ResponseEntity<>("Supplier Not Found", HttpStatus.NOT_FOUND);
        supplierService.deleteById(supplierId);
        return new ResponseEntity<>("Supplier Deleted",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierDTO supplierDTO){
        if(supplierService.findById(supplierDTO.getId())==null) return new ResponseEntity<>("No Supplier Found",HttpStatus.NOT_FOUND);
        supplierService.updateSupplier(supplierDTO);
        return new ResponseEntity<>("Supplier Updated",HttpStatus.OK);
    }

    @GetMapping("/by-id/{supplierId}")
    public ResponseEntity<?> getSupplierById(@PathVariable("supplierId") String supplierId){
        SupplierDTO response  = supplierService.findById(supplierId);
        if(response==null) return new ResponseEntity<>("No Supplier Found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}
