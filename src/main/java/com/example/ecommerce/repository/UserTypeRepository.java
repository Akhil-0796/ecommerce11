package com.example.ecommerce.repository;

import com.example.ecommerce.model.UserType;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserTypeRepository extends CassandraRepository<UserType,String> {
}
