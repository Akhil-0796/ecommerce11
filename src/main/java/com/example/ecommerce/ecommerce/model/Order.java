package com.example.ecommerce.ecommerce.model;

import com.example.ecommerce.ecommerce.Enum.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("order")
public class Order {

    @PrimaryKey
    @Column(value = "order_id")
    private String orderId;

    @Column(value = "order_value")
    private Double value;
    private String orderStatus;
    @Column(value = "order_time")
    private LocalDateTime orderTime;
    @Column(value = "last_updated")
    private LocalDateTime lastUpdated;
    private List<String> items;

}
