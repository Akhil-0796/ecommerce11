package com.example.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetails {

    private String orderId;
    private Double orderValue;
    private String method;
    private String intent;
    private String currency;
    private String description;
}
