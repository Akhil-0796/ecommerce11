package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String orderId;
    private Double value;
    private String orderStatus;
    private LocalDateTime orderTime;
    private LocalDateTime lastUpdated;
    private List<String> items;
}
