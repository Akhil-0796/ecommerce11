package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class UserType {

    @PrimaryKey
    private String id;

    private String name;
}
