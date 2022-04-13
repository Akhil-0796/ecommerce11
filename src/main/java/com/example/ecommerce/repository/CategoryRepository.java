package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CassandraRepository<Category,String>{
}
