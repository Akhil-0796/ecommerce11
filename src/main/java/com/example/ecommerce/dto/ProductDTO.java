package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {


    private String productId;
    private String productName;
    private int categoryId;
    private Double price;
    private Double discount;
    private Integer rating;
    private List<String> reviews;
    private Integer stock;
    private String description;
    private String supplierId;
}
