package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {
    @AllowFiltering
    User findByEmail(String email);

    @AllowFiltering
    User findByName(String name);
}
