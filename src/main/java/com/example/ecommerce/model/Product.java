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
public class Product {

    @PrimaryKey
    @Column(value = "product_id")
    private String productId;

    @Column(value = "product_name")
    private String productName;
    @Column(value = "category_id")
    private int categoryId;
    private Double price;
    private Double discount;
    private Integer rating;
    private List<String> reviews;
    private Integer stock;
    private String description;
    @Column(value = "supplier_id")
    private String supplierId;




}
