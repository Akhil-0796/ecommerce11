package com.example.ecommerce.controller;

import com.example.ecommerce.dto.SupplierDTO;
import com.example.ecommerce.model.Supplier;
import com.example.ecommerce.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * will add new supplier
     * @param supplier
     * @return added supplier
     */
    @PostMapping("/add")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO supplier){
        SupplierDTO response =  supplierService.addSupplier(supplier);
        if (response==null) return new ResponseEntity<>("Supplier Already Present",HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * will return all the supplier available
     * @return list of supplier
     */
    @GetMapping("/get-all")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    /**
     * will remove supplier from system based on id
     * @param supplierId
     * @return response message
     */
    @DeleteMapping("/delete-by-id/{supplierId}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("supplierId") String supplierId){
        if(supplierService.findById(supplierId)==null) return new ResponseEntity<>("Supplier Not Found", HttpStatus.NOT_FOUND);
        supplierService.deleteById(supplierId);
        return new ResponseEntity<>("Supplier Deleted",HttpStatus.OK);
    }

    /**
     * will update the supplier details
     * @param supplierDTO
     * @return updated supplier
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierDTO supplierDTO){
        if(supplierService.findById(supplierDTO.getId())==null) return new ResponseEntity<>("No Supplier Found",HttpStatus.NOT_FOUND);
        supplierService.updateSupplier(supplierDTO);
        return new ResponseEntity<>("Supplier Updated",HttpStatus.OK);
    }

    /**
     * will return the supplier based on id
     * @param supplierId
     * @return supplier
     */
    @GetMapping("/by-id/{supplierId}")
    public ResponseEntity<?> getSupplierById(@PathVariable("supplierId") String supplierId){
        SupplierDTO response  = supplierService.findById(supplierId);
        if(response==null) return new ResponseEntity<>("No Supplier Found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}
