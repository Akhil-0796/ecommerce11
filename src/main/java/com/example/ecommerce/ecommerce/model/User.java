package com.example.ecommerce.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private UUID id;
    private String name;
    private String user_name;
    private String password;
    private List<Integer> order_ids;
    private String email;
    private String mobile;
    private LocalDateTime creation_time;
    private String user_type;

}
