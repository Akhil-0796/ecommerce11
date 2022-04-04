package com.example.ecommerce.ecommerce.model;

import com.example.ecommerce.ecommerce.Enum.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("order")
public class Order {

    @PrimaryKey
    private UUID order_id;
    private Double order_value;
    private String orderStatus;
    private LocalDateTime order_time;
    private LocalDateTime last_updated;

}
