package com.example.ecommerce.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {

    @PrimaryKey
    private UUID product_id;

    private String product_name;
    private int category_id;
    private Double price;
    private Double discount;
    private Integer rating;
    private List<Integer> reviews;
    private Integer stock;
    private String description;
    private UUID supplier_id;




}
