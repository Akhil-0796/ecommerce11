package com.example.ecommerce.ecommerce.repository;

import com.example.ecommerce.ecommerce.model.Category;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CassandraRepository<Category,Integer>{
}
