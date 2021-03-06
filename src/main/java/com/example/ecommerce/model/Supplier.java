package com.example.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Supplier {

    @PrimaryKey
    private String id;
    @Column(value = "supplier_name")
    private String supplierName;
    @Column(value = "address")
    private String address;
    @Column(value = "category")
    private List<String> categories;


}
