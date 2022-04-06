package com.example.ecommerce.ecommerce.model;


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
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {

    @PrimaryKey
    private String id;
    private String name;
    @Column(value = "user_name")
    private String email;

    private String password;
    @Column(value = "order_ids")
    private List<String> orderIds;

    private String mobile;
    @Column(value = "creation_time")
    private LocalDateTime creationTime;
    private String role;

}
